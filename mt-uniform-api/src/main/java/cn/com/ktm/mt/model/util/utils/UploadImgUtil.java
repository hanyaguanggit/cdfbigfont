package cn.com.ktm.mt.model.util.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

public class UploadImgUtil {
    private static String allowSuffix = "jpg,png,gif,jpeg";//允许文件格式
    private static long allowSize = 2L * 1024 * 1024;//允许文件大小
    private  final static Logger logger = LoggerFactory.getLogger(UploadImgUtil.class);
    private static String getFileNameNew() {
//      SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//      return fmt.format(new Date());
      return String.valueOf(UUID.randomUUID());
  }

   /* public static Map<String, String> uploadImg(String savepath, MultipartFile ut, ServletContext sc,
                                                String imgName) throws IllegalStateException, IOException {
        String basepath = sc.getRealPath("/");
        String path = basepath + "upload/" + savepath;
        Map<String, String> map = new TreeMap<String, String>();
        String oldPath = path + "/" + imgName;
        File oldfile = new File(path);
        if (!oldfile.exists()) {
            oldfile.mkdirs();
        }
        File fileOldPath = new File(oldPath);
        ut.transferTo(fileOldPath);
        map.put("urlPath", oldPath.replace(basepath, "/"));
        return map;
    }*/
    public static String upload(MultipartFile file, String destDir, HttpServletRequest request) {
        //String path = request.getContextPath();
        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
          /*  int length = allowSuffix.indexOf(suffix);
            if (length == -1) {
                throw new Exception("请上传允许格式的文件");
            }
            if (file.getSize() > allowSize) {
                throw new Exception("您上传的文件大小已经超出范围");
            }*/

            File destFile = new File(destDir);
            if (!destFile.exists()) {
                throw new RuntimeException("上传文件路径不存在");
            }
            String fileNameNew = getFileNameNew() + "." + suffix;
            String address = destFile.getAbsoluteFile() + "/" + fileNameNew;
            File f = new File(address);
            file.transferTo(f);
            logger.error("上传成功，文件名称{}",fileNameNew);
            //f.createNewFile();
            //fileName = basePath+destDir+fileNameNew;
            return fileNameNew;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            logger.info("上传失败");
            throw new RuntimeException(e.getMessage());
        }
    }

}
