package com.example.study_webapp.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.lang.Exception;
import java.util.List;
import java.util.Map;

@Service
public interface FileUploadService {

    Map<String, Object> selectGroup() throws Exception;

    Map<String, Object> insertAttachFile(List<MultipartFile> fileList, Map<String, Object> param) throws Exception;

    List<Map<String,Object>> selectAttachFileListByIDX(Map<String, Object> param) throws Exception;

    List<Map<String, Object>> selectAttachFileListByGroup(Map<String, Object> param) throws Exception;

    void deleteFileAttach(Map<String, Object> param) throws Exception;

    void selectAttachFileDownload(HttpServletResponse request, HttpServletResponse response, Map<String, Object> param)throws Exception;

}
