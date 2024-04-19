package com.example.webinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Controller
public class WebInfoApplication {
	List<Integer> numbers = List.of(10, 5, 3, 12, 6, 7, 5, 3);
	public static void main(String[] args) {
		SpringApplication.run(WebInfoApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/even")
	public String evenNumbers(Model model) {
		List<Integer> evens = numbers.stream()
				.filter(n -> n % 2 == 0)
				.collect(Collectors.toList());
		model.addAttribute("result", evens);
		return "result";
	}

	@GetMapping("/greaterthanfive")
	public String greaterThanFive(Model model) {
		List<Integer> greaterThanFive = numbers.stream()
				.filter(n -> n > 5)
				.collect(Collectors.toList());
		model.addAttribute("result", greaterThanFive);
		return "result";
	}
	@GetMapping("/max")
	public String max(Model model) {
		int max = numbers.stream().max(Integer::compareTo).orElseThrow();
		model.addAttribute("result", max);
        return "result";
    }
	@GetMapping("/min")
	public String min(Model model) {
		int min = numbers.stream().min(Integer::compareTo).orElseThrow();
		model.addAttribute("result", min);
		return "result";
	}
	@GetMapping("/sum")
	public String sum(Model model) {
		int sum = numbers.stream().reduce(0, Integer::sum);
		model.addAttribute("result", sum);
		return "result";
	}
	@GetMapping("/distinctNumbers")
	public String distinctNumbers(Model model) {
		List<Integer> distinctNumbers = numbers.stream().distinct().toList();
		model.addAttribute("result", distinctNumbers);
		return "result";
	}
	@GetMapping("/firstElements")
	public String firstElements(Model model) {
		List<Integer> firstElements = numbers.stream().limit(5).toList();
		model.addAttribute("result", firstElements);
		return "result";
	}
	@GetMapping("/subList")
	public String subList(Model model) {
		List<Integer> subList = numbers.stream().skip(2).limit(5).toList();
		model.addAttribute("result", subList);
		return "result";
	}
	@GetMapping("/firstGreaterThan5")
	public String firstGreaterThan5(Model model) {
		Integer firstGreaterThan5 = numbers.stream().filter(n -> n > 5).findFirst().orElse(null);
		model.addAttribute("result", firstGreaterThan5);
		return "result";
	}
}
