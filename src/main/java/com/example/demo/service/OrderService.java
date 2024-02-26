package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.Student;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository itemRepository;
    private StudentService studentService;
    private StudentRepository studentRepository;

    public void verification(Long id) {
        Optional<Student> existStudent = studentRepository.findById(id);
        if (!existStudent.isPresent()) {
            System.out.println("id가 존재하지않습니다 구매할수없습니다");
        }
    }

    public void orderItem(String itemName, Long id) throws Exception {
        verification(id);
        Optional<Item> existItem = itemRepository.findByItemName(itemName);
        if(existItem.isPresent() && existItem.get().getStockQuantity() > 0){
            Item item = existItem.get();
            item.removeStock(1);  //내가 주문한 갯수만큼 재고 없애버리고
            Student student = new Student();
            item.setItemName(itemName);
            student.increaseGrade();

            studentRepository.save(student);
            itemRepository.save(item);
            System.out.println("남은 아이템 갯수->" + item.getStockQuantity());
        }
        else {
            System.out.println("재고가 없다");
        }
    }
}
