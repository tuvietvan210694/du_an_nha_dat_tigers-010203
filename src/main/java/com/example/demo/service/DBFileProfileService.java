package com.example.demo.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.DBFile;
import com.example.demo.entity.DBFileProfile;
import com.example.demo.exception.FileStorageException;
import com.example.demo.exception.MyFileNotFoundException;
import com.example.demo.repository.DBFileProfileRepository;

@Service
public class DBFileProfileService {
	
	@Autowired
    private DBFileProfileRepository dBFileProfileRepository;

    public DBFileProfile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFileProfile dbFile = new DBFileProfile(fileName, file.getContentType(), file.getBytes());

            return dBFileProfileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    
    public DBFileProfile getFile(Long fileId) {
        return dBFileProfileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

}
