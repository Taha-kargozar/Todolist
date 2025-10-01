package org.todolist.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PageController {
    @GetMapping
    public String Homepage() {return "Homepage";}

    @GetMapping("/add")
    public String AddTodolist() {return "AddTodolist";}

    @GetMapping("/all")
    public String ListsTodo() {return "ListsTodo";}
}
