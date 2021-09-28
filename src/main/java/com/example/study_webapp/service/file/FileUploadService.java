package com.example.study_webapp.service.file;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FileUploadService {


    List<Map<String,Object>> selectAttachFileListByIDX(Map<String, Object> param) throws Exception;


}
