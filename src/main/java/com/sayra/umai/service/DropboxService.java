package com.sayra.umai.service;

import com.dropbox.core.DbxException;
import org.springframework.web.multipart.MultipartFile;

public interface DropboxService {
  String uploadFile(MultipartFile file, String subfolder) throws Exception;

  private String createSharedLink(String pathLower) {
    return null;
  }

  void deleteFile(String filepath) throws DbxException;
  int cleanOldTempFiles(String subfolder, int daysOld);


}
