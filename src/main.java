
package src;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
    main Inicio = new main();
    }
    Scanner sc = new Scanner(System.in);
    int Max = 30;
    int ContadorCliente = 0;
    int ContadorPeli = 0;
    int ContadorPrestamo = 0;
    int ContadorCategorias = 0;
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
    //Arreglos de utilidades:
    String[] Categorias = new String[Max];
    //Constructor:
    public main(){
        ImpresionMenu1();
    }
    //Metodos de Muestra de menus
    public void ImpresionMenu1(){
        System.out.println("\n============Menu Principal=================");
        System.out.println("\n1) Prestamos de peliculas");
        System.out.println("2) Devolución de películas");
        System.out.println("3) Mostrar  datos de las películas");
        System.out.println("4) Ingresar nueva película");
        System.out.println("5) Ordenar las películas de forma ascendente respecto al nombre");
        System.out.println("6) Ingresar clientes nuevos");
        System.out.println("7) Mostrar clientes");
        System.out.println("8) Menu de Reportes");
        System.out.println("9) Salir");
        System.out.println("Ingrese su opcion a elegir:");
        int opcion = sc.nextInt();
        System.out.println("====================================================");
        EleccionDelMenu1(opcion);
    }
    //
    public void EleccionDelMenu1(int O){
        switch(O){
            case 1:
                MenuPrestamoPeli();  
                break;
            case 2:
                DevolucionDePeli();  
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
                MenuImpresoDeReporte();  
                break;
            case 9:                  
                break;
            default : 
                System.out.println("Opción seleccionada invalida vuelva a intentarlo:");
                ImpresionMenu1();
                break;                
        }
    }
    //Para el Menu del caso 1:
    public void MenuPrestamoPeli(){
        Scanner sl = new Scanner(System.in);
        if(ContadorPeli!=0&&ContadorCliente!=0){
        System.out.println("\n==========Menu de Prestamo===============");
        System.out.println("Listado de peliculas:");
        System.out.println("Nombre|Id");
            for(int x = 0 ; x < ContadorPeli ; x++){
                if(Pelis_Disponible[x]){
                    System.out.println(Pelis_Nombre[x]+" | "+Pelis_Id[x]);
                }
            }
        MenuPrestamoPeli2(); 
        }else{
            System.out.println("\nNo se pueden realizar pestamos sin clientes o peliculas en el sistema, porfavor ingresar alguno.");
            System.out.println("Presione Enter para regresar al menu");
            String a = sl.nextLine();
            ImpresionMenu1();        
        }
    }
    public void MenuPrestamoPeli2(){
        Scanner sl = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente que rentará la pelicula:");
        int Op = sc.nextInt();
        //if que detectará si el id existe
        if(ClienteDisponible(Op)!=-1){            
            //if que detectará si tiene una pelicula rentada
            if(ClienteDisponible(Op)!=-2){
                if(PeliDisponible(Op)!=-2){
            System.out.println("Ingrese el id de la pelicula que rentará");
            int IdP = sc.nextInt();
            System.out.println("Ingrese los días de renta");
            int Dias = sc.nextInt();
            AgregarPrestamo(Op,IdP,Dias,ClienteDisponible(Op),PeliDisponible(IdP));
            System.out.println("====================================================");
            ImpresionMenu1();
            }else{
            System.out.println("\nEl id del cliente ingresado ya tiene una pelicula rentada en estos momentos");
            System.out.println("Intente con otro id");
            MenuPrestamoPeli2();
            }
            }else{
            System.out.println("\nEl id del cliente ingresado ya tiene una pelicula rentada en estos momentos");
            System.out.println("Intente con otra pelicula");
            MenuPrestamoPeli2();
            }
        }else{
        System.out.println("El id ingresado no existe en la base de datos, porfavor compruebe si está correcto o intente con otro.");
        ImpresionMenu1();
        }
        System.out.println("Presione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();    
    }
    //Case 2 Menu principal: Devolución de peliculas:
    public void DevolucionDePeli(){
        Scanner sl = new Scanner(System.in);
        if(ContadorPrestamo!=0){
        System.out.println("Todos los prestamos en el sistema son:");
        System.out.println("Nombre del cliente | Id del cliente | Nombre de la pelicula | Id de la pelicula ");            
                for(int x = 0 ; x < ContadorPrestamo ; x++){
                    System.out.println(NombreDeClienteID(Prestamo_IdCliente[x])+" | "+Prestamo_IdCliente[x]+" | "+NombreDePeliculaID(Prestamo_IdPeli[x])+" | "+Prestamo_IdPeli[x]);
                }
                System.out.println("Escriba el Id de la pelicula que se devolverá:"); 
                int IdP = sc.nextInt();
                System.out.println("Escriba el Id del cliente que devolverá la pelicula:"); 
                int IdC = sl.nextInt();
                System.out.println(IdP+ " / "+IdC);
                DevolverPelicula(IdP, IdC);
                System.out.println("Presione Enter para regresar al menu");
                String a = sc.nextLine();        
                ImpresionMenu1();
        }else{
            System.out.println("\nNo hay prestamos activos en el sistema.");
            System.out.println("Presione Enter para regresar al menu");
            String a = sl.nextLine();
            ImpresionMenu1();
        }
            
    }
    //Para el Menu de mostrar datos de peliculas del caso 3
    public void MenuMostrarPelis(){
    Scanner sl = new Scanner(System.in);
    if(ContadorPeli!=0){
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
    }else{
        System.out.println("\nNo hay peliculas dentro del sistema.");
        System.out.println("Presione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();        
    }
    }
    //Para el menu del caso 4 creación de plis:
    public void MenuCrearPeli(){
        Scanner sl = new Scanner(System.in);
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
                System.out.println("===================================================");
                ImpresionMenu1();
            }else{
                System.out.println("Ya existe una pelicula con ese id, porfavor seleccione otro.");
                MenuCrearPeli();
            }
    }
    
    //Para el menu del caso 6 creacion de cliente nuevo:
    public void MenuCrearCliente(){
        Scanner sl = new Scanner(System.in);
        System.out.println("\n============Menu de ingreso de Cliente===============");
        System.out.println("Ingrese el numero id que le asignara al Cliente nuevo:");
        int Id = sc.nextInt();
            if(ClienteDisponible(Id)==-1){
                System.out.println("Ingrese el nombre del cliente:");
                String N = sl.nextLine();
                System.out.println("Ingrese el numero de telefono del cliente:");
                int T = sc.nextInt();
                AgregarCliente(Id, N, T);
                System.out.println("=========================================");
                ImpresionMenu1();
            }else{
                System.out.println("Ya existe un cliente con ese id, porfavor seleccione otro.");
                MenuCrearCliente();
            }
    }
    //Para el menu del caso 7 Mostrando datos de clientes:
    public void MenuMostrarCliente(){
        Scanner sl = new Scanner(System.in);
        if(ContadorCliente!=0){
        System.out.println("\nLos Clientes dentro del sistema son:");
        System.out.println("El formato para mostrar la información de los clientes es:");
        System.out.println("Id | Nombre | telefono | Tiene pelicula prestada");
           for(int x = 0 ; x < ContadorCliente ; x++){
               System.out.println(Cliente_Id[x]+" | "+Cliente_Nombre[x]+" | " + Cliente_Telefono[x]+" | "+ClientePeliPrestada(Cliente_PeliPres[x]));
           }
        }else{
        System.out.println("\nNo hay clientes dentro del sistema.");
        System.out.println("\nPresione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();        
    }
        System.out.println("\nEstos son todos los clientes en el sistema");
        System.out.println("Presione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();    
    }
    //Para el menu del caso 8 el cual es el menu de reportes:
    public void MenuImpresoDeReporte(){
    Scanner sl = new Scanner(System.in);
    if(ContadorPeli!=0){    
        System.out.println("\n==========Menu de Reportes===============");
        System.out.println("\n1) Cantidad de películas por categoría");
        System.out.println("2) Las películas de una categoría en específico");
        System.out.println("3) cantidad de veces que se han prestado las peliculas");
        System.out.println("4) Pelicula más prestada");
        System.out.println("5) Pelicula menos prestada");
        System.out.println("6) Regresar al menu principal");
        System.out.println("Ingrese su opcion a elegir:");
        int opcion = sc.nextInt();
        System.out.println("=========================================");
        EleccionMenuReporte(opcion);
    }else{
        System.out.println("\nNo se pueden generar reportes sin peliculas dentro del sistema");
        System.out.println("\nPresione Enter para regresar al menu");
        String a = sl.nextLine();
        ImpresionMenu1();          
    }
    }
    public void EleccionMenuReporte(int Opci){
            switch(Opci){
            case 1:
                CategoriasExistentes();
                PeliculasPorCategoria(); 
                break;
            case 2:
                CategoriasExistentes();
                System.out.println("Desea mostrar todas las categorias(1) o buscar una en especifico(2):");
                int opcion = sc.nextInt();
                    if(opcion==1){
                        PeliculasSegunCategoria();}
                    else if(opcion==2){
                        PeliculasCategoriaEspecifico();
                    }else{
                        System.out.println("Opción seleccionada invalida vuelva a intentarlo:");
                        EleccionMenuReporte(2);
                    }
                break;
            case 3:
                MostrarVecesPrestada();
                break;
            case 4:
                PeliMasPrestada();  
                break;    
            case 5:
                PeliMenosPrestada();  
                break;
            case 6:
                ImpresionMenu1();
                break;
            default : 
                System.out.println("Opción seleccionada invalida vuelva a intentarlo.");
                MenuImpresoDeReporte();
                break;
        }
    }
        //Encargado del Case 1 del menu de reportes el cual muestra cuantas peliculas hay según su categoria: 
        public void PeliculasPorCategoria(){
            Scanner sl = new Scanner(System.in);
            System.out.println(ContadorCategorias);
            for(int x = 0 ; x < ContadorCategorias; x++){
                int Cont=0;
                for(int y = 0 ; y < ContadorPeli ; y++){                    
                        if(Categorias[x].equals(Pelis_Categoria[y])){
                            Cont++;                    
                        }      
                }
            System.out.println("\nLa cantidad total de Peliculas de "+Categorias[x]+" es de: "+Cont);
            }
            System.out.println("\nPresione Enter para regresar al menu");
            String a = sl.nextLine();            
            MenuImpresoDeReporte();    
        }
        //Case 2 del menu de reportes muestra según las categorias cuales peliculas pertenecen a esta
        public void PeliculasSegunCategoria(){
            Scanner sl = new Scanner(System.in);
            for(int x = 0 ; x < ContadorCategorias ; x++){
                System.out.println("\nLas peliculas que perteneces al genero "+Categorias[x]+" son:");
                for(int y = 0 ; y < ContadorPeli ; y++){                    
                        if(Categorias[x].equals(Pelis_Categoria[y])){
                            System.out.println(Pelis_Nombre[y]);                    
                        }      
                }
            }
            System.out.println("Presione Enter para regresar al menu");
            String a = sl.nextLine();            
            MenuImpresoDeReporte();
        }
        public void PeliculasCategoriaEspecifico(){
            Scanner sl = new Scanner(System.in);
            boolean llave = true;
            System.out.println("Escriba la categoria que desea buscar:\n(Recuerde que si no está igual que en el sistema no encontrará peliculas iguales)");
            String cat = sl.nextLine();
            System.out.println("\nLas peliculas que perteneces al genero "+cat+" son:");
                for(int x = 0 ; x < ContadorPeli ; x++){                                            
                    if(Pelis_Categoria[x].equals(cat)){
                        System.out.println(Pelis_Nombre[x]);
                        llave = false;
                    }      
                }
            if(llave){
            System.out.println("No se encontró ninguna pelicula que pertenezca a la categoria "+cat);
            }    
            System.out.println("\nPresione Enter para regresar al menu");
            String a = sl.nextLine();            
            MenuImpresoDeReporte();    
        }
        //Case 3 del menu de reportes: Cantidad de veces que se a prestado las peliculas.
        public void MostrarVecesPrestada(){
        Scanner sl = new Scanner(System.in);   
        System.out.println("\nEl numero de veces que se han prestado las peliculas dentro del sistema son:");
        System.out.println("El formato de la información es");
        System.out.println("Nombre de la pelicula | Veces prestada");
           for(int x = 0 ; x < ContadorPeli ; x++){
               System.out.println(Pelis_Nombre[x]+" | " + Pelis_VecesPrestadas[x]);
           }
        System.out.println("\nEsas son todas las peliculas en el sistema");
        System.out.println("\nPresione Enter para regresar al menu");
        String a = sl.nextLine();
        MenuImpresoDeReporte();
        }
        //case 4 del menu de reportes: Buscar la pelicula más prestada
        public void PeliMasPrestada(){
        Scanner sl = new Scanner(System.in);    
        int IndiceLaMas = 0;
        int LaMas = 0;
            for(int x = 0 ; x < ContadorPeli ; x++){
                if(LaMas<Pelis_VecesPrestadas[x]){
                    LaMas=Pelis_VecesPrestadas[x];
                    IndiceLaMas = x;
                }                   
            }
        System.out.println("La Pelicula más prestada es:" + Pelis_Nombre[IndiceLaMas]);
        System.out.println("\nPresione Enter para regresar al menu");
        String a = sl.nextLine();            
        MenuImpresoDeReporte();
        }
        //Case 5 del menu de reportes: la peliculas menos prestada
        public void PeliMenosPrestada(){
        Scanner sl = new Scanner(System.in);    
        int IndiceLaMenos = 0;
        int LaMenos = 100;
            for(int x = 0 ; x < ContadorPeli ; x++){
                if(LaMenos>Pelis_VecesPrestadas[x]){
                    LaMenos=Pelis_VecesPrestadas[x];
                    IndiceLaMenos = x;
                }                   
            }
        System.out.println("La Pelicula menos prestada es:" + Pelis_Nombre[IndiceLaMenos]); 
        System.out.println("\nPresione Enter para regresar al menu");
        String a = sl.nextLine();            
        MenuImpresoDeReporte();
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
    public int ClientePorId(int id){
        for(int x = 0 ; x < ContadorCliente ; x++){
            if(Cliente_Id[x]==id){
                    return x;
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
    public int PeliPorId(int id){
        for(int x = 0 ; x < ContadorPeli ; x++){
            if(Pelis_Id[x]==id){
                return x;
            }
        }
        return -1;
    }    
    //Metodo de busqueda de categorias:
    public void CategoriasExistentes(){
        AgregarCategoria();
        boolean llave = false;
        for(int x = 0 ; x < ContadorPeli ; x++){
            for(int y = 0 ; y < ContadorCategorias ; y++){
                 if(!(Pelis_Categoria[x].equals(Categorias[y]))){
                    llave = true; 
                     for(int i = 0 ; i < ContadorCategorias ; i++){
                        if(Pelis_Categoria[x].equals(Categorias[i])){
                            llave = false;
                        }
                     }
                if(llave){
                Categorias[ContadorCategorias] = Pelis_Categoria[x];
                ContadorCategorias++;
                }     
            }
                //No haciendo nada si encuentra categorias iguales                
            }
        }
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
    public void AgregarCategoria(){
        if(ContadorCategorias==0){
        Categorias[ContadorCategorias] = Pelis_Categoria[0];
        ContadorCategorias++;
        }
    }
    //Metodos de devolución/eliminación.
    public void DevolverPelicula(int IdP, int IdC){
        for(int x = 0 ; x < ContadorPrestamo ; x++){
            if(IdP==Prestamo_IdPeli[x]){
                if(IdC==Prestamo_IdCliente[x]){
                //con esto borraremos la devolución
                for (int i = x; i < ContadorPrestamo; i++) {
                    Prestamo_IdPeli[i] = Prestamo_IdPeli[i+1];
                    Prestamo_IdCliente[i] = Prestamo_IdCliente[i+1];
                    Prestamo_Dias[i] = Prestamo_Dias[i+1];
                    Cliente_PeliPres[ClientePorId(IdC)] = false;
                    Pelis_Disponible[PeliPorId(IdP)] = true;
                    System.out.println("\nSe a devuelto la pelicula con exito"); 
                }
                ContadorPrestamo--;
                }else{
                System.out.println("\nNo existe un prestamo con ese id de pelicula y cliente, porfavor revise la información");     
                }
            }else{
            System.out.println("\nNo existe un prestamo con ese id de pelicula y cliente, porfavor revise la información"); 
            }
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
    public String NombreDeClienteID(int id){
        for(int x = 0 ; x < ContadorCliente ; x++){    
            if(id==Cliente_Id[x]){
                return Cliente_Nombre[x];
            }
        }
        return "el cliente con el id asignado no tiene nombre registrado";
    }
    public String NombreDePeliculaID(int id){
        for(int x = 0 ; x < ContadorPeli ; x++){    
            if(id==Pelis_Id[x]){
                return Pelis_Nombre[x];
            }
        }
        return "la pelicula con el id asignado no tiene nombre registrado";
    }    
}
