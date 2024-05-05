/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.DAAP01Practca04.DAAP01Practca04;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author francisco
 */
@RestController
@RequestMapping("/api/v1")
public class EmpleadosControler {
    @Autowired
    private EmpleadoRepository repositoryEmpleado;
    @GetMapping("/empleado/")
    public List<Empleado> list() {
        return repositoryEmpleado.findAll();
    }
    
        @GetMapping("/empleado_get/{id}")
    public Empleado get(@PathVariable long id) {
        Optional<Empleado> optEmpleado = repositoryEmpleado.findById(id);
         return optEmpleado.orElse(null);
        }
        
    
    
    @PutMapping(value= "/empleado_put/{id}", produces = "application/json")
        public Empleado put(@PathVariable("id") Long id, @RequestBody Empleado emp) {
            Optional<Empleado> optionalEmpleado = repositoryEmpleado.findById(id);
            if (optionalEmpleado.isPresent()) {
                Empleado empleado = optionalEmpleado.get();
                empleado.setClave(emp.getClave());
                empleado.setNombre(emp.getNombre());
                empleado.setDireccion(emp.getDireccion());
                empleado.setTelefono(emp.getTelefono());
                return repositoryEmpleado.save(empleado);
            } else {
                return null;
            }
        }
    
    
    @PostMapping ("/empleado_post")
  public Empleado post(@RequestBody Empleado emp) {
            return repositoryEmpleado.save(emp);
        }
    
    @DeleteMapping("/empleado_delete/{id}")
       public void delete(@PathVariable("id") Long id) {
            repositoryEmpleado.deleteById(id);
        }
    
}
