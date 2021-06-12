
package src;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
    main Inicio = new main();
    }
    Scanner sc = new Scanner(System.in);
    //Escaner para lineas
    Scanner sl = new Scanner(System.in);
    int Max = 100;
    int ContadorCliente = 0;
    int ContadorPeli = 0;
    int ContadorPrestamo = 0;
    
    //Arreglos para guardar los clientes
    String [] Cliente_Nombre = new String [Max];
    int [] Cliente_Id = new int[Max];
    int [] Cliente_Telefono = new int[Max];
    boolean [] Cliente_PeliPres = new boolean[Max];
    
    //Arreglos para guardar las pelis
    int[] Pelis_Id = new int[Max];
    String[] Pelis_Nombre = new String[Max];
    int [] Pelis_Fecha = new int[Max];
    String[] Pelis_Categoria = new String[Max];
    boolean[] Pelis_Disponible = new boolean[Max];
    int[] Pelis_VecesPrestadas = new int[Max];
    
    //Arreglos para guardar los prestamos
    int[] Prestamo_IdPeli = new int[Max];
    int[] Prestamo_IdCliente = new int[Max];
    int[] Prestamo_Dias = new int[Max];
    //Constructor:
    public main(){
        ImpresionMenu1();
    }
    //Metodos de Muestra de menus
    public void ImpresionMenu1(){
        System.out.println("\n==========Menu Principal===============");
        System.out.println("\n1) Prestamos de peliculas");
        System.out.println("2) Devolución de películas");
        System.out.println("3) Mostrar  datos de las películas");
        System.out.println("4) Ingresar nueva película");
        System.out.println("5) Ordenar las películas de forma ascendente respecto al nombre");
        System.out.println("6) Ingresar clientes nuevos");
        System.out.println("7) Mostrar clientes");
        System.out.println("8) Reportes");
        System.out.println("9) Salir");
        System.out.println("Ingrese su opcion a elegir:");
        int opcion = sc.nextInt();
        EleccionDelMenu1(opcion);
    }
    //
    public void EleccionDelMenu1(int O){
        switch(O){
            case 1:
                MenuPrestamoPeli();  
                break;
            case 2:
                  
                break;
            case 3:
                MenuMostrarPelis();  
                break;
            case 4:
                MenuCrearPeli();  
                break;    
            case 5:
                  
                break;
            case 6:
                MenuCrearCliente();  
                break;
            case 7:
                MenuMostrarCliente();  
                break;
            case 8:
                  
                break;
            case 9:
                  
                break;    
        }
    }
    //Para el Menu del caso 1:
    public void MenuPrestamoPeli(){
        System.out.println("\n==========Menu de Prestamo===============");
        System.out.println("Listado de peliculas:");
        System.out.println("Nombre|Id");
            for(int x = 0 ; x < ContadorPeli ; x++){
                if(Pelis_Disponible[x]){
                    System.out.println(Pelis_Nombre[x]+" | "+Pelis_Id[x]);
                }
            }
        MenuPrestamoPeli2();    
    }
    public void MenuPrestamoPeli2(){
        System.out.println("Ingrese el id del cliente que rentará la pelicula:");
        int Op = sc.nextInt();
        //if que detectará si el id existe
        if(ClienteDisponible(Op)!=-1){
            //if que detectará si tiene una pelicula rentada
            if(ClienteDisponible(Op)!=-2){
            System.out.println("Ingrese el id de la pelicula que rentará");
            int IdP = sc.nextInt();
            System.out.println("Ingrese los días de renta");
            int Dias = sc.nextInt();
            AgregarPrestamo(Op,IdP,Dias,ClienteDisponible(Op),PeliDisponible(IdP));
            ImpresionMenu1();
            }else{
            System.out.println("El id del cliente ingresado ya tiene una pelicula rentada en estos momentos");
            System.out.println("Intente con otro id");
            MenuPrestamoPeli2();
            }    
        }else{
        System.out.println("El id ingresado no existe en la base de datos, porfavor compruebe si está correcto o intente con otro.");
        ImpresionMenu1();
        }
    }
    //Para el Menu de mostrar datos de peliculas del caso 3
    public void MenuMostrarPelis(){        
        System.out.println("\nLas peliculas dentro del sistema son:");
        System.out.println("El formato de la información de la pelicula es:");
        System.out.println("Id | Nombre | Año | Categoria | Disponible");
           for(int x = 0 ; x < ContadorPeli ; x++){
               System.out.println(Pelis_Id[x]+" | "+Pelis_Nombre[x]+" | " + Pelis_Fecha[x]+" | "+Pelis_Categoria[x]+" | "+PeliDisponible(Pelis_Disponible[x]));
           }
        System.out.println("\nEsas son todas las peliculas en el sistema");
        System.out.println("Presione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();
    }
    //Para el menu del caso 4 creación de plis:
    public void MenuCrearPeli(){
        System.out.println("\n============Menu de ingreso de pelicula===============");
        System.out.println("Ingrese el numero id que le asignara a la pelicula:");
        int Id = sc.nextInt();
            if(PeliDisponible(Id) == -1){
                System.out.println("Ingrese el nombre de la pelicula:");
                String N = sl.nextLine();
                System.out.println("Ingrese el año de salida de la pelicula:");
                int A = sc.nextInt();
                System.out.println("Ingrese la categoria de la pelicula:");
                String C = sl.nextLine();
                AgregarPelicula(Id,N,A,C);
                ImpresionMenu1();
            }else{
                System.out.println("Ya existe una pelicula con ese id, porfavor seleccione otro.");
                MenuCrearPeli();
            }
    }
    
    //Para el menu del caso 6 creacion de cliente nuevo:
    public void MenuCrearCliente(){
        System.out.println("\n============Menu de ingreso de Cliente===============");
        System.out.println("Ingrese el numero id que le asignara al Cliente nuevo:");
        int Id = sc.nextInt();
            if(ClienteDisponible(Id)==-1){
                System.out.println("Ingrese el nombre del cliente:");
                String N = sl.nextLine();
                System.out.println("Ingrese el numero de telefono del cliente:");
                int T = sc.nextInt();
                AgregarCliente(Id, N, T);
                ImpresionMenu1();
            }else{
                System.out.println("Ya existe un cliente con ese id, porfavor seleccione otro.");
                MenuCrearCliente();
            }
    
    }
    //Para el menu del caso 7 Mostrando datos de clientes:
    public void MenuMostrarCliente(){
        System.out.println("\nLos Clientes dentro del sistema son:");
        System.out.println("El formato para mostrar la información de los clientes es:");
        System.out.println("Id | Nombre | telefono | Tiene pelicula prestada");
           for(int x = 0 ; x < ContadorCliente ; x++){
               System.out.println(Cliente_Id[x]+" | "+Cliente_Nombre[x]+" | " + Cliente_Telefono[x]+" | "+Pelis_Categoria[x]+" | "+ClientePeliPrestada(Cliente_PeliPres[x]));
           }
        System.out.println("\nEstos son todos los cñientes en el sistema");
        System.out.println("Presione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();    
    
    }
    //Metodos de busqueda de datos de los clientes:
    public int ClienteDisponible(int id){
        for(int x = 0 ; x < ContadorCliente ; x++){
            if(Cliente_Id[x]==id){
                if(!(Cliente_PeliPres[x])){
                    return x;
                }else{
                return -2;
                }
            }
        }
        return -1;
    }
    //Metodos de busqueda de datos de las peliculas:
    public int PeliDisponible(int id){
        for(int x = 0 ; x < ContadorPeli ; x++){
            if(Pelis_Id[x]==id){
                if(Pelis_Disponible[x]){
                    return x;
                }else{
                return -2;
                }
            }
        }
        return -1;
    }    
    //Metodos de agregación de datos
    public void AgregarPrestamo(int IdCliente, int IdPeli, int Dias, int PosCliente,int PosPeli){
        if(ContadorPrestamo==Max){
            System.out.println("Tamaño maximo alcanzado");
        }else{
            Prestamo_IdCliente[ContadorPrestamo] = IdCliente;
            Prestamo_IdPeli[ContadorPrestamo] = IdPeli;
            Prestamo_Dias[ContadorPrestamo] = Dias;
            ContadorPrestamo++;
            Cliente_PeliPres[PosCliente] = true;
            Pelis_Disponible[PosPeli] = false;
            Pelis_VecesPrestadas[PosPeli]++;
            System.out.println("El prestamo se a agregado con exito");     
        }
    }
    public void AgregarPelicula(int Id, String Nombre, int A, String Cate){
        if(ContadorPrestamo==Max){
            System.out.println("Tamaño maximo de peliculas alcanzado");
        }else{
            Pelis_Id[ContadorPeli] = Id;
            Pelis_Nombre[ContadorPeli] = Nombre;
            Pelis_Fecha[ContadorPeli] = A;
            Pelis_Categoria[ContadorPeli] = Cate;
            Pelis_Disponible[ContadorPeli] = true;
            Pelis_VecesPrestadas[ContadorPeli] = 0;
            ContadorPeli++;
            System.out.println("\nLa pelicula se a agregado con exito"); 
        }
    }
    public void AgregarCliente(int Id, String Nombre, int Telefono){
        if(ContadorPrestamo==Max){
            System.out.println("Tamaño maximo de peliculas alcanzado");
        }else{
            Cliente_Id[ContadorCliente]= Id;
            Cliente_Nombre[ContadorCliente] = Nombre;
            Cliente_Telefono[ContadorCliente] = Telefono;
            Cliente_PeliPres[ContadorCliente] = false;
            ContadorCliente++;
            System.out.println("\nEl cliente se a agregado con exito");       
        }
    }
    //Metodos de cambios de Información
    public String PeliDisponible(boolean a){
        if(a){
        return "Disponible";
        }else{
        return "No está Disponible";
        }
    }
    public String ClientePeliPrestada(boolean a){
        if(a){
        return "Si";
        }else{
        return "No";
        }
    }    
}
