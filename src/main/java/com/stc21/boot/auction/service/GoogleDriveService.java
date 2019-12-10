package com.stc21.boot.auction.service;

import com.google.api.services.drive.model.File;
import com.stc21.boot.auction.entity.Lot;
import com.stc21.boot.auction.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoogleDriveService {

    List<Photo> uploadLotMedia(Lot currLot, Long lotID, MultipartFile[] file) throws IOException;

    List<Photo> uploadAvatarMedia(Lot currLot, Long userID, MultipartFile[] file) throws IOException;

    List<String> getUserImage(Long userID) throws IOException;

    List<String> getLotImage(Long lotID) throws IOException;

    void deleteFileOrFolder(String fileId) throws IOException;

    String checkFolderExist(String parentFolderID, String FolderName) throws IOException;

    List<File> getFolderByName(String parentFolderID, String subFolderName) throws IOException;

    List<File> getSubFolders(String folderIdParent) throws IOException;

}
