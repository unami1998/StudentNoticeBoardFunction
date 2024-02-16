package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.Student;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository itemRepository;
    private StudentService studentService;
    private StudentRepository studentRepository;
    public boolean orderItem(ItemDto itemDto, Long id) throws Exception {
        verification(id);
        Order order = new Order();
        Item item = new Item();
        if(item.getStockQuantity()<1){
            System.out.println("재고가 없다");
            return false;
        }
        else {
            item.setItemName(itemDto.getItemName());
            item.setStockQuantity(itemDto.getItemCount());
            order.getOrderItems().add(item);
            item.removeStock(itemDto.getItemCount());
            studentService.pointStudent(id);

            System.out.println("남은 아이템 갯수->"+item.getStockQuantity());

        }
        return true;
    }
    public void verification(Long id){
        Optional<Student> existStudent = studentRepository.findById(id);
        if(!existStudent.isPresent()){
            System.out.println("id가 존재하지않습니다 구매할수없습니다");
        }
    }

}
