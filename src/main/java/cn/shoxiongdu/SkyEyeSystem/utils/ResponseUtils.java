package cn.shoxiongdu.SkyEyeSystem.utils;

import cn.hutool.core.io.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.OutputStream;

@Slf4j
public class ResponseUtils {

    @SneakyThrows
    public static void writeFile(byte[] file, HttpServletResponse response, String fileName) {
        response.setContentType("text/txt");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        log.info("data.length " + file.length);
        response.setContentLength(file.length);
        response.setHeader("Content-Range", String.valueOf(Integer.valueOf(file.length - 1)));
        response.setHeader("Accept-Ranges", "bytes");
        OutputStream os = response.getOutputStream();
        os.write(file);
        os.flush();
        os.close();
    }

    @SneakyThrows
    public static void writeFile(File file, HttpServletResponse response, String fileName) {
        writeFile(FileUtil.readBytes(file), response, fileName);
    }

}
