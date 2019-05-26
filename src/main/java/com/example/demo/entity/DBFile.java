package com.example.demo.entity;

import java.util.Arrays;

//import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "files")
public class DBFile {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//	private String id;
    
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;
    
    @JsonIgnore
    @JsonIgnoreProperties(value = "product")
    @OneToOne(mappedBy = "dBFile", orphanRemoval = true)
    private Product product;

    public DBFile() {

    }

    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
    



public DBFile(Long id, String fileName, String fileType, byte[] data, Product product) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.product = product;
	}

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
    
    
    

    public String getFileName() {
        return fileName;
    }

    
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

//	@Override
//	public String toString() {
//		return "DBFile [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", data="
//				+ Arrays.toString(data) + ", product=" + product + "]";
//	}
    
}
