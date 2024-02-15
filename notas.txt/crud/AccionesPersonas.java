import java.util.*;

public class AccionesPersona {

    /**Vamos a hacer un crud */
    //mostrar personas

    public ArrayList<Persona> listaPersonas = new ArrayList<Persona>(null);
    
    //listar a las personas
    public ArrayList<Persona> mostrarPersonas(){
        return listaPersonas;
    }

    //registrar
    public void agregarPersona(Personap){
        listaPersonas.add(p);
    }
    
    //buscar persona
    public Persona buscarPersona(int id){
        Persona encontrada = new Persona();
        
        for(Personap: listaPersonas){
            if(id == p.getId()){
                encontrada = p;
            }else{
                System.out.println("Persona no encontrada");
            }
        }
        return encontrada;

        }


    //actualizar
    public void actualizarPersona(Persona actualizada){
        Persona actualizar = buscarPersonas(actualizada.getId());
        listaPersonas.remove(actualizar);
        listaPersonas.add(actualizar);
    }

    public void eliminarPersona(Persona eliminar){
        listaPersonas.remove(eliminar);
        
    }

}