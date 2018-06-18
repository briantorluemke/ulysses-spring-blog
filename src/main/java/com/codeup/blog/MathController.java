package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{x}/and/{y}")
    public @ResponseBody int add(
            @PathVariable int x,
            @PathVariable int y
    ) {
      return (x+y);
    }

    @GetMapping("/subtract/{x}/and/{y}")
    public @ResponseBody int subtract(
            @PathVariable int x,
            @PathVariable int y
    ) {
        return (x-y);
    }

    @GetMapping("/multiply/{x}/and/{y}")
    public @ResponseBody int multiply(
            @PathVariable int x,
            @PathVariable int y
    ) {
        return (x*y);
    }

    @GetMapping("/divide/{x}/and/{y}")
    public @ResponseBody int divide(
            @PathVariable int x,
            @PathVariable int y
    ) {
        return (x/y);
    }

}
