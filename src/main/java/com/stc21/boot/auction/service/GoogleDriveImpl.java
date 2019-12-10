package com.stc21.boot.auction.service;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.entity.Photo;
import com.stc21.boot.auction.utils.GoogleDriveUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GoogleDriveImpl implements GoogleDriveService {

    private final String ROOT_FOLDER_NAME = "Reduction";
    private final String AVATAR_FOLDER_NAME = "avatars";
    private final String LOT_FOLDER_NAME = "lots";
    private final String RootFolderID;
    private final String AvatarsFolderID;
    private final String LotsFolderID;
    private Drive driveService;


    public GoogleDriveImpl() throws IOException {
        this.driveService = GoogleDriveUtils.getDriveService();
        List<String> listID = createFolderSystem();
        this.RootFolderID = listID.get(0);
        this.AvatarsFolderID = listID.get(1);
        this.LotsFolderID = listID.get(2);
    }

    @Override
    public List<Photo> uploadLotMedia(Lot currLot, Long lotID, MultipartFile[] file) throws IOException {
        return uploadMedia(currLot, lotID, file, LotsFolderID);
    }

    @Override
    public List<Photo> uploadAvatarMedia(Lot currLot, Long userID, MultipartFile[] file) throws IOException {
        return uploadMedia(currLot, userID, file, AvatarsFolderID);
    }

    @Override
    public List<String> getUserImage(Long userID) throws IOException {
        return getImages(userID, AvatarsFolderID);
    }

    @Override
    public List<String> getLotImage(Long lotID) throws IOException {
        return getImages(lotID, LotsFolderID);
    }

    @Override
    public void deleteFileOrFolder(String fileId) throws IOException {
        driveService.files().delete(fileId).execute();
    }

    @Override
    public String checkFolderExist(String parentFolderID, String FolderName) throws IOException {
        List<File> filesList = getFolderByName(parentFolderID, FolderName);
        return filesList.isEmpty() ? "" : filesList.get(0).getId();
    }

    @Override
    public List<File> getFolderByName(String parentFolderID, String subFolderName) throws IOException {
        String query;
        if (parentFolderID == null) {
            query = " name = '" + subFolderName + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
                    + " and 'root' in parents";
        } else {
            query = " name = '" + subFolderName + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
                    + " and '" + parentFolderID + "' in parents";
        }

        return getContent(query);
    }

    @Override
    public List<File> getSubFolders(String folderIdParent) throws IOException {
        final String mimeType = "mimeType = 'application/vnd.google-apps.folder'";
        return getContent(createQuery(mimeType, folderIdParent));
    }

    private List<Photo> uploadMedia(Lot currLot, Long id, MultipartFile[] file, String parentFolder) throws IOException {
        String folderID = foldersTrack(id, parentFolder);
        List<Photo> list = new ArrayList<>();
        for (MultipartFile filePart : file) {
            File diskDriveFile = createFile(folderID, filePart.getContentType(), filePart.getOriginalFilename(), filePart.getBytes());
            list.add(new Photo(currLot, diskDriveFile.getWebContentLink()));
        }
        return list;
    }

    private List<String> getImages(Long id, String parentFolderID) throws IOException {
        List<File> list = getFolderByName(parentFolderID, String.valueOf(id));
        List<String> listImageURL;
        if (list.isEmpty()) {
            throw new FileNotFoundException("Folder is not exist");
        } else {
            String folderID = list.get(0).getId();
            list = getFiles(folderID);
            listImageURL = list
                    .stream()
                    .map(File::getWebContentLink)
                    .collect(Collectors.toList());
        }
        return listImageURL;
    }


    private String foldersTrack(Long id, String parentFolderID) throws IOException {
        String folderID = checkFolderExist(parentFolderID, String.valueOf(id));
        if (folderID.isEmpty()) {
            folderID = createFolder(parentFolderID, String.valueOf(id)).getId();
        }
        return folderID;
    }

    private List<String> createFolderSystem() throws IOException {
        List<String> foldersIDs = new ArrayList<>();

        String tempID = checkFolderExist(null, ROOT_FOLDER_NAME);
        foldersIDs.add((tempID.isEmpty()) ? createFolder(null, ROOT_FOLDER_NAME).getId() : tempID);

        tempID = checkFolderExist(foldersIDs.get(0), AVATAR_FOLDER_NAME);
        foldersIDs.add((tempID.isEmpty()) ? createFolder(foldersIDs.get(0), AVATAR_FOLDER_NAME).getId() : tempID);

        tempID = checkFolderExist(foldersIDs.get(0), LOT_FOLDER_NAME);
        foldersIDs.add((tempID.isEmpty()) ? createFolder(foldersIDs.get(0), LOT_FOLDER_NAME).getId() : tempID);

        return foldersIDs;
    }

    private File createDiskDriveFile(String googleFolderIdParent, String contentType, String customFileName, AbstractInputStreamContent uploadStreamContent) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(customFileName);
        fileMetadata.setParents(Collections.singletonList(googleFolderIdParent));

        File file = driveService
                .files()
                .create(fileMetadata, uploadStreamContent)
                .setFields("*")
                .execute();
        return file;
    }


    private List<File> getFiles(String folderIdParent) throws IOException {
        final String mimeType = "mimeType != 'application/vnd.google-apps.folder'";
        return getContent(createQuery(mimeType, folderIdParent));
    }


    // mimeType in format "mimeType = 'application/vnd.google-apps.folder'" or "mimeType != 'application/vnd.google-apps.folder'"
    private String createQuery(String mimeType, String folderIdParent) {
        String query = null;
        if (folderIdParent == null) {
            query = mimeType + " and 'root' in parents";
        } else {
            query = mimeType + " and '" + folderIdParent + "' in parents";
        }
        return query;
    }


    private List<File> getContent(String query) throws IOException {
        String pageToken = null;
        List<File> list = new ArrayList<File>();
        do {
            FileList result = driveService
                    .files()
                    .list()
                    .setQ(query)
                    .setSpaces("drive")
                    .setFields("nextPageToken, files(*)")  //.setFields("nextPageToken, files(id, name, createdTime, size)")//
                    .setPageToken(pageToken).execute();
            list.addAll(result.getFiles());
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        return list;
    }

    private File createFolder(String parentFolderID, String folderName) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");

        if (parentFolderID != null) {
            fileMetadata.setParents(Collections.singletonList(parentFolderID));
        }
        File file = driveService.files().create(fileMetadata).setFields("*").execute();
        return file;
    }

    private File createFile(String googleFolderIdParent, String contentType, String customFileName, byte[] uploadData) throws IOException {
        AbstractInputStreamContent uploadStreamContent = new ByteArrayContent(contentType, uploadData);
        return createDiskDriveFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
    }

    public String getRootFolderID() {
        return RootFolderID;
    }

    public String getAvatarsFolderID() {
        return AvatarsFolderID;
    }

    public String getLotsFolderID() {
        return LotsFolderID;
    }
}
