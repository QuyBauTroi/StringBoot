package com.example.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

// @Bean phải được định nghĩa trong class được chú thích bởi @Configuration
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);

        // 1. Duyệt numbers
        numbers.forEach(System.out::println);

        // 2. Tìm các giá trị chẵn trong list
        List<Integer> evenNumbers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Các số chẵn: " + evenNumbers);

        // 3. Tìm các giá trị > 5 trong list
        List<Integer> greaterThan5 = numbers.stream()
                .filter(num -> num > 5)
                .collect(Collectors.toList());
        System.out.println("Các số lớn hơn 5: " + greaterThan5);

        // 4. Tìm giá trị max trong list
        int max = numbers.stream().max(Integer::compareTo).orElseThrow();
        System.out.println("Giá trị lớn nhất: " + max);

        // 5. Tìm giá trị min trong list
        int min = numbers.stream().min(Integer::compareTo).orElseThrow();
        System.out.println("Giá trị nhỏ nhất: " + min);

        // 6. Tính tổng các phần tử của mảng
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Tổng các phần tử: " + sum);

        // 7. Lấy danh sách các phần tử không trùng nhau
        List<Integer> distinctNumbers = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("Danh sách các phần tử không trùng nhau: " + distinctNumbers);

        // 8. Lấy 5 phần tử đầu tiên trong mảng
        List<Integer> first5Elements = numbers.stream().limit(5).collect(Collectors.toList());
        System.out.println("5 phần tử đầu tiên: " + first5Elements);

        // 9. Lấy phần tử từ thứ 3 -> thứ 5
        List<Integer> subList = numbers.stream().skip(2).limit(3).collect(Collectors.toList());
        System.out.println("Phần tử từ thứ 3 đến thứ 5: " + subList);

        // 10. Lấy phần tử đầu tiên > 5
        Integer firstGreaterThan5 = numbers.stream().filter(num -> num > 5).findFirst().orElse(null);
        System.out.println("Phần tử đầu tiên lớn hơn 5: " + firstGreaterThan5);

        // 11. Kiểm tra xem list có phải là list chẵn hay không
        boolean allEven = numbers.stream().allMatch(num -> num % 2 == 0);
        System.out.println("List có phải là list chẵn hay không: " + allEven);

        // 12. Kiểm tra xem list có phần tử > 10 hay không
        boolean anyGreaterThan10 = numbers.stream().anyMatch(num -> num > 10);
        System.out.println("List có phần tử lớn hơn 10 hay không: " + anyGreaterThan10);

        // 13. Có bao nhiêu phần tử > 5
        long countGreaterThan5 = numbers.stream().filter(num -> num > 5).count();
        System.out.println("Số lượng phần tử lớn hơn 5: " + countGreaterThan5);

        // 14. Nhân đôi các phần tử trong list và trả về list mới
        List<Integer> doubledNumbers = numbers.stream().map(num -> num * 2).collect(Collectors.toList());
        System.out.println("Danh sách sau khi nhân đôi: " + doubledNumbers);

        // 15. Kiểm tra xem list không chứa giá trị nào = 8 hay không
        boolean noneEquals8 = numbers.stream().noneMatch(num -> num == 8);
        System.out.println("List không chứa giá trị nào bằng 8: " + noneEquals8);
    }
}