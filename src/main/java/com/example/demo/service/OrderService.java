package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Student;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    private ItemRepository itemRepository;

    private OrderRepository orderRepository;
    private final StudentService studentService;
    private StudentRepository studentRepository;

    public OrderService(StudentService studentService) {
        this.studentService = studentService;
    }

    public long orderItem(String itemName, String studentName) throws Exception {
        Optional<Item> existItemOptional = itemRepository.findByItemName(itemName);

        if (existItemOptional.isPresent()) {
            Item existItem = existItemOptional.get();
            if (Objects.equals(existItem.getStudent().getName(), studentName)) {  //student 이름과 학생 이름이 맞지않다면
                existItem.removeStock(1);  //내가 주문한 갯수만큼 재고 없애버리고

                Orders order = new Orders();
                order.getOrderItems().add(existItem);
                orderRepository.save(order);
                itemRepository.save(existItem);
                Student student = new Student();
                student.increaseGrade();

                return existItem.getId();
            } else {
                return -1;
            }
        }else{
            return -1;
        }
    }
}