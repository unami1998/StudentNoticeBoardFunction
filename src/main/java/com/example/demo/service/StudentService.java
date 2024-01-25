package com.example.demo.service;


import com.example.demo.dto.Response.orderDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.Student;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ItemRepository itemRepository;

    //Student 추가
    public int join(Student student){
        validateDuplicateStudent(student);
        studentRepository.save(student); //이렇게 해도 추가가 된다
        return student.getId();
    }

    public void validateDuplicateStudent(Student student) {
        List<Student> existStudent = studentRepository.findByName(student.getName());  //이름으로 중복찾기
        if(!existStudent.isEmpty()){ //중복된 이름 empty가 아니라면 이미 누군가 그 이름을 쓴다는거니깐
            throw new DuplicateFormatFlagsException("이미 존재하는 이름");
        }
    }

    public void orderStudent(String name, String itemName) {
        Optional<Item> findItem = itemRepository.findByItemName(itemName);
        findItem.ifPresent(item -> {
            if(itemName != null){
                int stock = item.getStockQuantity()-1;
                item.setStockQuantity(stock);
                itemRepository.save(item);
            }
        });
    }
}
