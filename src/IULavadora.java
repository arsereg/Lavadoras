import java.io.*;
import java.util.ArrayList;

public class IULavadora {

	static BufferedReader in= new BufferedReader (new InputStreamReader(System.in));
	static PrintStream out =System.out;
	
	
	public static ArrayList listaLavadora = new ArrayList<Lavadora>();

	public static ArrayList<Ropa> listaRopa= new ArrayList<Ropa>();
	
	public static int lavadoraSeleccionada = -1;
	public static int lavadorasCreadas = 0;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		int opc;
		boolean noSalir = true;
		
		String listaMenu[] = {  "1.Crear lavadora",
					"2.Mostrar Lavadoras",
					"3.Seleccionar Lavadoras",
                                        "4.Mostrar Menu de Lavadora",
                                        "0.Salir"};
		
		do{
			mostrarMenu(listaMenu);
			opc = leerOpcion();
			noSalir = ejecutarAccionMenuPrincipal(opc);
		}while(noSalir);
	}
		static void mostrarMenu(String[] pmenu){
			for(int i=0;i<pmenu.length;i++){
				out.println(pmenu[i]);
			}
			
		}
                
		static int leerOpcion(){
			int opcion;
			out.println("Seleccione una opcion");
			try{
				opcion = Integer.parseInt(in.readLine());
			}catch(Exception ex){
				opcion = -1;
			}
			out.println();
			return opcion;
		}
		static boolean ejecutarAccionMenuPrincipal(int popcion) throws IOException, InterruptedException{
			boolean noSalir= true;
			switch(popcion){
			case 1:
				crearLavadora();
				break;
			case 2:
				mostrarLavadoras();
				break;
			case 3:
				seleccionarLavadoras();
				break;
                        case 4:
                                menuLavadoraSeleccionada();
                            break;
			case 0:
				noSalir = false;
				break;
			default:
				out.println("Opcion invalida");
				out.println();
				break;				
			}
			return noSalir;			
		}
		static void crearLavadora(){
			listaLavadora.add(new Lavadora());
                        lavadorasCreadas++;
			out.println("Se ha creado nueva lavadora");
			
		}
		static void mostrarLavadoras(){
                    out.println();
                    for(int i=1; i <= lavadorasCreadas; i++){
                        out.println("Lavadora: " + i);
                    }
                    out.println();
		}
		static void seleccionarLavadoras()throws java.io.IOException{
                    mostrarLavadoras();
			int seleccion = 0;
			out.println("Seleccione la lavadora que desee utilizar");
			try{
				seleccion = Integer.parseInt(in.readLine());
			}catch(Exception ex){
				out.println("Opcion invalida");
			}
                        lavadoraSeleccionada = seleccion -1;
		}
		static void menuLavadoraSeleccionada()throws java.io.IOException, InterruptedException{
                    if(lavadorasCreadas < 0){
                        out.println("Cree al menos una lavadora para continuar");
                    }else{
                        if(lavadoraSeleccionada == -1){
                            out.println("Debe seleccionar al menos una lavadora");
                        }else{
                            int opc;
                            boolean noSalir = true;
                            String listaMenu[] = {
                                "1.Cargar Agua",
                                "2.Cargar Ropa",
                                "3.Cargar Detergente",
                                "4.Especificar Tiempo",
                                "5.Drenar",
                                "6.Mostrar estado de la lavadora",
                                "7.Mostrar estado de la ropa",
                                "8.Pausar lavadora"
                            };
                            do{
                                mostrarMenuDos(listaMenu);
                                opc = leerOpcionDos();
                                noSalir = ejecutarAccionMenuDos(opc);
                            }while(noSalir);
                        }
                    }
                }   
                
		static void mostrarMenuDos(String[] pmenu){
			for(int i=0;i<pmenu.length;i++){
				out.println(pmenu[i]);
			}
		}
		static int leerOpcionDos(){
			int opcion;
			out.println("Seleccione una opcion");
			try{
				opcion = Integer.parseInt(in.readLine());
			}catch(Exception ex){
				opcion = -1;
			}
			out.println();
			return opcion;
		}
		static boolean ejecutarAccionMenuDos(int popcion)throws java.io.IOException, InterruptedException{
			
			boolean noSalir= true;
			switch(popcion){
			case 1:
				cargarAgua();
				break;
			case 2:
				cargarRopa();
				break;
			case 3:
				cargarDetergente();
				break;
			case 4:
				especificarTiempo();
				break;
			case 5:
				drenar();
				break;
			case 6:
				mostarEstadoLavadora();
				break;
			case 7:
				mostrarEstadoRopa();
				break;
			case 8:
				pausarLavadora();
				break;
			case 9:
				noSalir = false;
				break;
			default:
				out.println("Opcion invalida");
				out.println();
				break;				
			}
			return noSalir;			
		}
		static void cargarAgua()throws java.io.IOException{
                    double agua;
                    boolean invalid = false;
                    do{
                        invalid = false;
                        out.println("Digite el porcentaje de agua que desea cargar");
                        agua = Double.parseDouble(in.readLine());
                        if(agua < 0 || agua > 100){
                            invalid = true;
                            out.println("Ese es un numero valido. Digite un numero entre 1 y 100");
                        }
                    }while(invalid);
                    
                    Lavadora lav = (Lavadora) listaLavadora.get(lavadoraSeleccionada);
                    lav.cargarAgua(agua);
                    out.println(agua + " cargada en la lavadora");
		}
		static void cargarRopa()throws java.io.IOException{
			int opcion = -1;		
			int suciedad = 10;
			out.println("1. Muy Sucia \n.2. Sucia \n3. Ligeramente Sucia \n4. Limpia");
			try{
                            opcion = Integer.parseInt(in.readLine());
			}catch(Exception ex){
				out.println("Opcion invalida");
			}
                        switch(opcion){
                            case 1:
                                suciedad = 10;
                                break;
                            case 2:
                                suciedad = 6;
                                break;
                            case 3:
                                suciedad = 3;
                                break;
                            case 4:
                                suciedad = 0;
                                break;
                        }
                        crearRopa(suciedad);
                        Lavadora lav = (Lavadora) listaLavadora.get(lavadoraSeleccionada);
			lav.setPrenda(listaRopa.get(listaRopa.size() -1));
		}
		static void cargarDetergente(){
                    Lavadora lav = (Lavadora) listaLavadora.get(lavadoraSeleccionada);
                    lav.cargarDetergente();
                    out.println("Detergente cargado");
		}
		static void especificarTiempo()throws java.io.IOException{
			int tiempo;
                        out.println("Digite cuantos minutos desea programar");
                        tiempo = Integer.parseInt(in.readLine());
                        Lavadora lav = (Lavadora) listaLavadora.get(lavadoraSeleccionada);
			lav.especificarTiempo(tiempo);
                        out.println(tiempo + " minutos especificados");
                        lav.arrancar();
		}
		static void drenar(){
                    Lavadora lav = (Lavadora) listaLavadora.get(lavadoraSeleccionada);
                    lav.drenar();
                    out.println("Lavadora drenada");
		}
		static void mostarEstadoLavadora(){
			String msm = "";
			msm =listaLavadora.get(lavadoraSeleccionada).toString();
			out.println(msm);
		}
		static void mostrarEstadoRopa(){
			String msm = "";
			msm = listaRopa.get(lavadoraSeleccionada).toString();
			out.println(msm);
		}
		static void pausarLavadora() throws InterruptedException{
                    Lavadora lav = (Lavadora) listaLavadora.get(lavadoraSeleccionada);
			lav.pausar();
                        out.println("Lavadora pausada");
		}
		static void crearRopa(int psuciedad){
			listaRopa.add(new Ropa(psuciedad));			
		}
}
