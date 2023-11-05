package pro.sky.telegram_chat_zooShelter.model;


import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.pengrad.telegrambot.model.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.File;
//import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//import java.io.*;


public class UploadPhoto {

    public static MultipartFile createMultipartFileFromPhotoSize(PhotoSize[] photoSizes) throws IOException {
        // Выбираем фото с максимальным размером (обычно это последний элемент массива)
        PhotoSize photoSize = photoSizes[photoSizes.length - 1];
        // Сохраняем фото на сервере
        File file = new File();
        file.setFilePath("${photo_pet}");
        file.setFileSize(Long.valueOf(photoSize.fileSize()));
        FileOutputStream output = new FileOutputStream(photoSize.toString());
        output.write(photoSize.fileSize());
        output.close();
        // Создаем объект MultipartFile из сохраненного файла
        return new MockMultipartFile("photo",
                file.toString(),
                "image/jpeg",
                new FileInputStream(file.toString()));
    }
}
