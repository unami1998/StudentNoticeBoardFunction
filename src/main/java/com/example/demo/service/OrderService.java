package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Student;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StudentRepository studentRepository;
    public String orderItem(String studentName,String itemName) throws Exception {
        Optional<Item> existItemOptional = itemRepository.findByItemName(itemName);

        List<Student> orderStudent = studentRepository.findByName(studentName);

        if (existItemOptional.isPresent()) {
            Item existItem = existItemOptional.get();
            for(Student student : orderStudent) {
                if (student.getName().equals(studentName)) {
                    existItem.removeStock(1);
                    Orders order = new Orders();
                    order.getOrderItems().add(existItem);
                    orderRepository.save(order);
                    itemRepository.save(existItem);
                    student.increaseGrade();

                    studentRepository.save(student);
                    System.out.println("학생 이름:" + studentName + "학생 포인트" + student.getPointCount());
                    return "주문 성공";
                }
            }return "해당 학생 찾을 수 없다";
        }else{
            return "상품 정보 문제";
        }
    }
}