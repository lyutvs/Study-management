package com.example.study_webapp.service.file;

import com.example.study_webapp.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service("FileUploadServie")
public class FileUploadServiceImpl implements FileUploadService{

    @Autowired
    private CommonDao commonDao;

    @Override
    public Map<String, Object> selectGroup() throws IOException {
        return null;
    }

    @Override
    public Map<String, Object> insertAttachFile(List<MultipartFile> fileList, Map<String, Object> param) throws IOException {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectAttachFileListByIDX(Map<String, Object> param) throws IOException {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectAttachFileListByGroup(Map<String, Object> param) throws IOException {
        return null;
    }

    @Override
    public void deleteFileAttach(Map<String, Object> param) throws IOException {

    }

    @Override
    public void selectAttachFileDownload(HttpServletResponse request, HttpServletResponse response, Map<String, Object> param) throws Exception {

    }


}
