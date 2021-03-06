/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sc.proyectoprogra.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Angelica
 */
public class Aplicacion {
    
    //Se crea variable para llevar el conteo de las aplicaciones
    int cantAplicaciones=0;
    //Se crea una variable para guardar los random
    int numAplicacion=0;
    //Se crea fechaAplicacion de tipo calendar
    Calendar fechaAplicacion;
    
    //Se crea una instancia de la clase doctor, cliente y examen
    Doctor doctores = new Doctor();
    Cliente clientes = new Cliente();
    Examen examenes = new Examen();
    
    //Se crea una matriz para guardar los datos de las aplicaciones de 100 campos
    public String aplicaciones[][]=new String[20][5];
        
    //Se crea una variable sdf de tipo SimpleDateFormat
    SimpleDateFormat sdf;
             
    //Se crea un constructor de aplicacion
    public Aplicacion(){
        //Se crea un formato para la fecha
        sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    }
    
    public void listarAplicaciones(){
        //Se verifica si ya se han agregado aplicaciones
        if (cantAplicaciones>0){
            System.out.println("");
             System.out.println("****** Aplicaciones de Examenes ******");
             //Se recorre toda la matriz y se imprimen los valores que no sean null
            for(int i=0;i<cantAplicaciones;i++){
                if(aplicaciones[i][0]!=null){
                //Si ya se han agregado se muestran en pantalla
                System.out.println("");
                System.out.println( "Numero de Aplicacion: "+aplicaciones[i][0]);
                int codExamen = Integer.parseInt(aplicaciones[i][1]);
                //Se relaciona el codigo del examen con el nombre del examen y se imprime
                for (int idExamen=0;idExamen < examenes.codigo.length;idExamen++){
                    if(codExamen==examenes.codigo[idExamen]){
                        System.out.println( "Examen: "+examenes.nombre[idExamen]);
                    }
                }
                int codDoct = Integer.parseInt(aplicaciones[i][2]);
                //Se relaciona el carnet del doctor con el nombre y apellidos del doctor y se imprime
                for (int idDoct=0;idDoct < doctores.carnet.length;idDoct++){
                    if(codDoct==doctores.carnet[idDoct]){
                        System.out.println( "Doctor a cargo: "+doctores.nombre[idDoct]+" "+doctores.primerApellido[idDoct]+" "+doctores.segundoApellido[idDoct]);
                    }
                }
                for (int idCli=0;idCli < clientes.cedula.length;idCli++){
                    //Se relaciona la cedula del cliente con el nombre y apellidos del cliente y se imprime
                    if(aplicaciones[i][3].equals(clientes.cedula[idCli])){
                        System.out.println( "Cliente: "+clientes.nombre[idCli]+" "+clientes.primerApellido[idCli]+" "+clientes.segundoApellido[idCli]);
                    }
                }
                System.out.println( "Fecha: "+aplicaciones[i][4]);
                }
            }
        }else{
            //Si no se han agregado aplicaciones, mostrar mensaje
             JOptionPane.showMessageDialog(null, "No se han agregado aplicaciones");
        }
    }
    
    public void agregarAplicaciones(int cant) {
        //Se declaran variables de encontrado
        int encontradoCli=0;
        int encontradoDoc=0;
        int encontradoExam=0;
        //Se crean for para recorrer toda la matriz y se indica que en el campo que sea null se agrega la informacion
        for(int c=0;c<cant;c++){
           for(int i=0;i<100;i++ ){
                if(aplicaciones[i][0]==null){
                    String lectura = JOptionPane.showInputDialog("Ingrese la c??dula del cliente al que desea agendarle la aplicaci??n de un examen: ");
                    //Se verifica que la cedula exista en los clientes registrados
                    for (int buscarC = 0; buscarC < clientes.CantCliente(); buscarC++) {
                        if (clientes.cedula[buscarC].equals(lectura)) {
                            encontradoCli=1;
                            lectura = JOptionPane.showInputDialog("Ingrese el c??digo del examen: ");
                            int codigo = Integer.parseInt(lectura);
                            //Se verifica que el codigo del examen exista en los examenes registrados
                            for (int buscarCo = 0; buscarCo < examenes.CantExamenes(); buscarCo++) {
                                if (codigo == examenes.codigo[buscarCo]) {
                                    encontradoExam=1;
                                    lectura = JOptionPane.showInputDialog("Ingrese el carnet del doctor asignado: ");
                                    int carnet = Integer.parseInt(lectura);
                                    //Se verifica que el carnet del doctor exista en los doctores registrados
                                    for (int buscarCar = 0; buscarCar < doctores.CantDoctores(); buscarCar++) {
                                        if (carnet == doctores.carnet[buscarCar]) {
                                            encontradoDoc=1;
                                            //Si todo los datos anteriores coinciden, se agrega la aplicacion del examen
                                            //Se crea un numero random de aplicacion
                                            numAplicacion = (int) (Math.random() * 500) + 50;
                                            Random aleatorio;
                                            aleatorio = new Random();
                                            fechaAplicacion = Calendar.getInstance();
                                            //Se crea una fecha random para la aplicacion del examen
                                            fechaAplicacion.set(aleatorio.nextInt(1) + 2023, aleatorio.nextInt(12) + 1, aleatorio.nextInt(30) + 1);
                                            //Se imprime el numero y la fecha
                                            System.out.println("La aplicacion del examen #" + numAplicacion + " tiene fecha para " + sdf.format(fechaAplicacion.getTime()));
                                            cantAplicaciones = cantAplicaciones + 1;
                                            //Se llena la matriz con los datos generados y los datos del cliente, doctor y examen
                                            aplicaciones[i][0]=Integer.toString(numAplicacion);
                                            aplicaciones[i][1]=Integer.toString(examenes.codigo[buscarCo]);
                                            aplicaciones[i][2]=Integer.toString(doctores.carnet[buscarCar]);
                                            aplicaciones[i][3]=clientes.cedula[buscarC];
                                            aplicaciones[i][4]=sdf.format(fechaAplicacion.getTime());
                                            break;
                                        } 
                                    }
                                } 
                            }
                        } 
                    }
                    if(encontradoCli!=1){
                        //En caso de que la cedula no exista, mostrar mensaje
                        JOptionPane.showMessageDialog(null, "El cliente con esa c??dula no existe");
                    }
                    else if(encontradoExam!=1){
                        //En caso de que el codigo del examen no exista, mostrar mensaje
                        JOptionPane.showMessageDialog(null, "El examen con ese c??digo no existe");
                    }
                    else if(encontradoDoc!=1){
                        //En caso de que el carnet del doctor no exista, mostrar mensaje
                        JOptionPane.showMessageDialog(null, "El doctor con ese carnet no existe");
                    }
                    break;
                }
           }
        }

    }

    
    public void editarrAplicaciones(){
        if (cantAplicaciones>0){
            //Se crean variables para verificar si existen los datos
            int encontradoCod=0;
            int encontradoCarn=0;
            int encontradoNumA=0;
            //Se solicita el numero de aplicacion que se desea modificar
            String lectura = JOptionPane.showInputDialog("Ingrese el n??mero de la aplicaci??n examen que desea editar: ");
             for(int i=0;i<cantAplicaciones;i++){
                 //Se comprueba que la aplicacion exista
                 if(lectura.equals(aplicaciones[i][0])){
                 encontradoNumA=1;
                 //Si el numero existe, se solicitan los nuevos datos de la aplicacion del examen y se actualizan los anteriores
                 String codN = JOptionPane.showInputDialog("Ingrese el c??digo del nuevo examen: ");
                 int Ncodigo=Integer.parseInt(codN);
                 //Se verifica que el codigo del nuevo examen exista en los examenes registrados
                 for(int buscarExam=0;buscarExam<examenes.CantExamenes();buscarExam++){
                     if(Ncodigo==examenes.codigo[buscarExam]){
                         encontradoCod=1;
                     }
                 }
                  if(encontradoCod==1){
                     aplicaciones[i][1]=codN;
                     String carN = JOptionPane.showInputDialog("Ingrese el carnet del nuevo doctor: ");
                    int Ncarnet=Integer.parseInt(carN);
                    //Se verifica que el carnet del nuevo doctor exista en los doctores registrados
                    for(int buscarDoc=0;buscarDoc<doctores.CantDoctores();buscarDoc++){
                        if(Ncarnet==doctores.carnet[buscarDoc]){
                            encontradoCarn=1;
                        }
                    }
                    if(encontradoCarn==1){
                        aplicaciones[i][2]=carN;
                        //Se solicita si desea cambiar la fecha de aplicacion del examen
                        String fechaN = JOptionPane.showInputDialog("Desea cambiar la fecha de la aplicaci??n del examen: \n1- Si \n2- No");
                        int opcion=Integer.parseInt(fechaN);
                        if(opcion==1){
                            //En caso que si, se genera una nueva fecha aleatoria
                            Random aleatorio;
                            aleatorio = new Random();
                            fechaAplicacion = Calendar.getInstance();
                            //Se crea una fecha random para la aplicacion del examen
                            fechaAplicacion.set (aleatorio.nextInt(1)+2023, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
                            //Se imprime el numero y la fecha
                            System.out.println("La aplicacion del examen #"+numAplicacion+" tiene una nueva fecha para "+sdf.format(fechaAplicacion.getTime()));
                            aplicaciones[i][4]=sdf.format(fechaAplicacion.getTime());
                    }
                    //Se muestra mensaje de verificacion
                    JOptionPane.showMessageDialog(null, "La aplicaci??n del examen ha sido modificado");
                    }else{
                        //Si no existe el carnet del doctor, mostrar mensaje
                        JOptionPane.showMessageDialog(null, "El carnet del nuevo doctor no existe");
                    }
                    
                 }else{
                      //Si no existe el codigo del examen, mostrar mensaje
                     JOptionPane.showMessageDialog(null, "El c??digo del nuevo examen no existe");
                 }
                 
             }
             }
              if(encontradoNumA!=1){
                 //Si no existe el numero, mostrar mensaje
                  JOptionPane.showMessageDialog(null, "La aplicaci??n con el #"+lectura+ " no existe");
             }
         
       }else{
            //Si no se han agregado aplicaciones, mostrar mensaje
             JOptionPane.showMessageDialog(null, "No se han agregado aplicaciones");
        }
    }
    
    public void eliminarAplicaciones(){
        int encontrado=0;
        if (cantAplicaciones>0){
        //Se solicita el numero de aplicacion que se desea modificar
        String lectura = JOptionPane.showInputDialog("Ingrese el n??mero de la aplicaci??n examen que desea eliminar: ");
        for(int filas=0;filas<cantAplicaciones;filas++){
            //Se verifica que la aplicacion exista
                if(lectura.equals(aplicaciones[filas][0])){
                    //Si existe, todos los valores se asignan en null
                    aplicaciones[filas][0]=null;
                    aplicaciones[filas][1]=null;
                    aplicaciones[filas][2]=null;
                    aplicaciones[filas][3]=null;
                    aplicaciones[filas][4]=null;
                    encontrado=1;
                    //Se muestra mensaje de verificacion
                   JOptionPane.showMessageDialog(null, "La aplicaci??n del examen ha sido eliminado");
                   //cantAplicaciones=cantAplicaciones-1;
                   break;
            }
        }
        if(encontrado!=1){
            //Si no existe la aplicacion, mostrar mensaje
              JOptionPane.showMessageDialog(null, "La aplicaci??n con el #"+lectura+ " no existe");
        }
       }else{
            //Si no se han agregado aplicaciones, mostrar mensaje
             JOptionPane.showMessageDialog(null, "No se han agregado aplicaciones");
        }
}
    //Se crea la funcion para generar los reportes de los examenes aplicados y sus fechas
    public void GenerarReportes(){
        //Se verifica si ya se han agregado aplicaciones
        if (cantAplicaciones>0){
            System.out.println();
            System.out.println("********** Reportes **********");
            //Se crea un for para recorrer toda la matriz y se imprimen los datos que no esten en null
            for(int i=0;i<cantAplicaciones;i++){
                if(aplicaciones[i][0]!=null){
                    //Si ya se han agregado se muestran en pantalla;
                    System.out.println("");
                    //System.out.println("Aplicacion de examen #"+(i+1));
                    System.out.println( "Numero de Aplicacion: "+aplicaciones[i][0]);
                    int codExamen = Integer.parseInt(aplicaciones[i][1]);
                    for (int idExamen=0;idExamen < examenes.codigo.length;idExamen++){
                        if(codExamen==examenes.codigo[idExamen]){
                            System.out.println( "Nombre del Examen: "+examenes.nombre[idExamen]);
                        }
                    }
                    System.out.println( "Fecha: "+aplicaciones[i][4]);
                } 
            }
        }else{
            //Si no se han agregado aplicaciones, mostrar mensaje
             JOptionPane.showMessageDialog(null, "No se puede generar un reporte porque no se han agregado aplicaciones de examenes");
        }
    }
}
