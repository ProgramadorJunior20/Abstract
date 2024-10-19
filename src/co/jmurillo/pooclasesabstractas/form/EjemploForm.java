package co.jmurillo.pooclasesabstractas.form;

import co.jmurillo.pooclasesabstractas.form.elementos.*;
import co.jmurillo.pooclasesabstractas.form.elementos.select.Opcion;
import co.jmurillo.pooclasesabstractas.form.validador.*;

import java.util.ArrayList;
import java.util.List;

public class EjemploForm {
    public static void main(String[] args) {

        InputForm username = new InputForm("username");
        username.addValidador(new RequeridoValidador());

        InputForm password = new InputForm("clave", "password");
        password.addValidador(new RequeridoValidador());
        password.addValidador(new LargoValidador(5, 10));

        InputForm email = new InputForm("email", "email");
        email.addValidador(new RequeridoValidador());
        email.addValidador(new EmailValidador());

        InputForm edad = new InputForm("edad", "number");
        edad.addValidador(new NumeroValidador());


        TextAreaForm experiencia = new TextAreaForm("exp", 5, 9);

        SelectForm lenguage = new SelectForm("lenguaje");
        lenguage.addValidador(new NoNuloValidador());

        lenguage.addOpcion(new Opcion("1", "Java").setSelected())
        .addOpcion(new Opcion("2", "Python"))
        .addOpcion(new Opcion("3", "JavasScript"))
        .addOpcion(new Opcion("4", "TypeScript"))
        .addOpcion(new Opcion("5","PHP"));

        ElementoForm saludar = new ElementoForm("saludo") {
            @Override
            public String dibujarHTML() {
                return "<input disabled name = '"+ this.nombre +"' value = '" + this.valor + "'>";
            }
        };

        saludar.setValor("Hola que tal como estan!!?. Este campo esta desabilitado");
        username.setValor("John.doe");
        password.setValor("a1b2c");
        email.setValor("john.doe@correo.com");
        experiencia.setValor("...... un año ........");
        edad.setValor("28");
        //java.setSelected(true);

        List<ElementoForm> elementos = new ArrayList<>();
        elementos.add(username);
        elementos.add(password);
        elementos.add(email);
        elementos.add(experiencia);
        elementos.add(edad);
        elementos.add(lenguage);
        elementos.add(saludar);

        for (ElementoForm e: elementos){
            System.out.println(e.dibujarHTML());
            System.out.println("<br>");
        }

        elementos.forEach(e -> {
            if (!e.esValido()){
                e.getErrores().forEach(System.out::println);
            }
        });


    }
}
