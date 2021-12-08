/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fichajespi.fichajespidestopapp.httpClient;

import com.fichajespi.fichajespidestopapp.entity.Fichaje;
import com.fichajespi.fichajespidestopapp.entity.NumeroEmpleado;
import feign.Headers;
import feign.RequestLine;

/**
 *
 * @author alex
 */
@Headers({"Content-Type: application/json"})
public interface FeignController {
  
  @RequestLine("POST /fichaje/now")
  Fichaje fichar(NumeroEmpleado empleado);

}
