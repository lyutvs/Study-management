package com.example.study_webapp.service.file;

import com.example.study_webapp.dao.CommonDao;
import com.example.study_webapp.model.util.file.FileDownloadUtil;
import com.example.study_webapp.model.util.file.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service("FileUploadServie")
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private CommonDao commonDao;

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> selectGroup() throws Exception {

        return commonDao.selectOne("attach.selectMaxGrp");
    }

    @Override
    public Map<String, Object> insertAttachFile(List<MultipartFile> fileList, Map<String, Object> param) throws Exception {

        if ("".equals(param.get("FILE_GROUP"))) {
            commonDao.insert("attach.insertAttachFileGroup", param);
            @SuppressWarnings("unchecked")
            Map<String, Object> map = commonDao.selectOne("attach.selectMaxGrp");
            param.put("FILE_GROUP", map.get("FILE_GROUP"));
        }


        //FileUtils file = new FileUtils();
        FileUploadUtil file1 = new FileUploadUtil();
        Map<String, Object> data = file1.uploadFile(fileList, param);
        commonDao.insert("attach.insertAttachFile", data);

        @SuppressWarnings("unchecked")
        Map<String, Object> files = commonDao.selectOne("attach.selectAttachFileListByGroup");
        return files;


    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> selectAttachFileListByIDX(Map<String, Object> param) throws Exception {

        return commonDao.selectList("attach.selectAttachFileListByIDX", param);
    }

    @Override
    public List<Map<String, Object>> selectAttachFileListByGroup(Map<String, Object> param) throws Exception {

        return commonDao.selectList("attach.selectAttachFileListByGroup", param);
    }

    @Override
    public void deleteFileAttach(Map<String, Object> param) throws Exception {

        commonDao.delete("attach.deleteFileAttach", param);
    }

    @Override
    public void selectAttachFileDownload(HttpServletResponse request, HttpServletResponse response, Map<String, Object> param) throws Exception {

        Map<String, Object> file = commonDao.selectOne("attach.selectAttachFileDownload", param);
        FileDownloadUtil download = new FileDownloadUtil();
        download.downloadFile(request, response, file);
    }

}

