package cn.com.ktm.mt.model.util.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

public class WebUploaderUtil {

    private static String allowSuffix = "jpg,png,gif,jpeg";//允许文件格式
    private static long allowSize = 2L * 1024 * 1024;//允许文件大小
//    private String fileName;
//    private String[] fileNames;


    /**
     * <p class="detail">
     * 功能：重新命名文件
     * </p>
     *
     */
    private static String getFileNameNew() {
//        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        return fmt.format(new Date());
        return String.valueOf(UUID.randomUUID());
    }

    /**
     *
     * <p class="detail">
     * 功能：文件上传
     * </p>
     * @author wangsheng
     * @date 2014年9月25日
     * @param files
     * @param destDir
     * @throws Exception
     */
//    public void uploads(MultipartFile[] files, String destDir,HttpServletRequest request) throws Exception {
//        String path = request.getContextPath();
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
//        try {
//            fileNames = new String[files.length];
//            int index = 0;
//            for (MultipartFile file : files) {
//                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
//                int length = getAllowSuffix().indexOf(suffix);
//                if(length == -1){
//                    throw new Exception("请上传允许格式的文件");
//                }
//                if(file.getSize() > getAllowSize()){
//                    throw new Exception("您上传的文件大小已经超出范围");
//                }
//                String realPath = request.getSession().getServletContext().getRealPath("/");
//                File destFile = new File(realPath+destDir);
//                if(!destFile.exists()){
//                    destFile.mkdirs();
//                }
//                String fileNameNew = getFileNameNew()+"."+suffix;//
//                File f = new File(destFile.getAbsoluteFile()+"\\"+fileNameNew);
//                file.transferTo(f);
//                f.createNewFile();
//                fileNames[index++] =basePath+destDir+fileNameNew;
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//     

    /**
     * <p class="detail">
     * 功能：文件上传
     * </p>
     *
     * @param destDir
     * @throws Exception
     * @author wangsheng
     * @date 2014年9月25日
     */
    public static String upload(MultipartFile file, String destDir, HttpServletRequest request) {
        //String path = request.getContextPath();
        //String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            int length = allowSuffix.indexOf(suffix);
            if (length == -1) {
                throw new Exception("请上传允许格式的文件");
            }
            if (file.getSize() > allowSize) {
                throw new Exception("您上传的文件大小已经超出范围");
            }

            //String realPath = request.getSession().getServletContext().getRealPath("/");
            File destFile = new File(destDir);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            String fileNameNew = getFileNameNew() + "." + suffix;
            String address = destDir + "/" + fileNameNew;
            File f = new File(address);
            file.transferTo(f);
            //f.createNewFile();
            //fileName = basePath+destDir+fileNameNew;
            return fileNameNew;
        } catch (Exception e) {
            System.out.println("上传失败");
        }
        return null;
    }
}

