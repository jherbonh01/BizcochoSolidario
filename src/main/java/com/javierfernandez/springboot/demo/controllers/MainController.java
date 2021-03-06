package com.javierfernandez.springboot.demo.controllers;


import com.javierfernandez.springboot.demo.models.dao.ICLienteDao;
import com.javierfernandez.springboot.demo.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    @Qualifier("clienteDaoJPA")
    private ICLienteDao clienteDao;

    @RequestMapping(value = "/listar",method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo","Listado de clientes");
        model.addAttribute("clientes",clienteDao.findAll());
        return "listar";
    }

    @RequestMapping(value = "/form")
    public String crear(Map <String, Object> model){
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);

        model.put("titulo", "Formulario de Cliente");
        return "form";
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String guardar (Cliente cliente){
        clienteDao.save(cliente);
        return "redirect:listar";
    }

}
