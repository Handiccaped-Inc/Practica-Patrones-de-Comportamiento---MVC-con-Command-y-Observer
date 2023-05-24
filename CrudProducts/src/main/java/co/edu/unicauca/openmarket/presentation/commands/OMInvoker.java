/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahurtado
 */
public class OMInvoker {
    
    private List<OMCommand> myCommands;
    private List<OMCommand> unexecutedCommands;
    private OMCommand currentCommand;
    
    public OMInvoker(){
        myCommands = new ArrayList<>();
        unexecutedCommands = new ArrayList<>();
        currentCommand=null;
    }
    
    public void addCommand(OMCommand actualCommand){
        currentCommand = actualCommand;
        
    }
    
    public void execute(){
        //El comando añadido se establece como el actual
        this.addCommand(currentCommand);
        //Se ejecuta el comando actual
        currentCommand.make();
        //Se añade al historial de comandos
        myCommands.add(currentCommand);
        //Se limpia la lista de comandos desejecutados (Cuando se realiza una accion esos comandos se pierden, ese es el comportamiento normal de
        // las apps que conocemos)
        unexecutedCommands.clear();
    }
    
    public void unexecute(){
        if (!myCommands.isEmpty()){
            //Obtener el indice del ultimo comando ejecutado
            int index = myCommands.size()-1;
            //Obtener ese ultimo comando
            OMCommand unexecutedCommand= myCommands.get(index);
            //Deshacerlo
            unexecutedCommand.unmake();
            //Remover el comando deshecho
            myCommands.remove(index);
            //Añadir el comando a los comandos deshechos
            unexecutedCommands.add(unexecutedCommand);
        }
        
    }
    
    public void reexecute(){
        if(!unexecutedCommands.isEmpty()){
            int index = unexecutedCommands.size()-1;
            //Obtener ese ultimo comando deshecho
            OMCommand command= unexecutedCommands.get(index);
            //rehacerlo
            //Establecerlo como el comando actual
            this.addCommand(command);
            //Ejecutar el comando actual
            currentCommand.remake();
            //Se añade al historial de comandos ejecutados
            myCommands.add(currentCommand);
            //Remover el comando rehecho
            unexecutedCommands.remove(index);
        }
    }
    
    
    
    public boolean hasMoreCommands(){
        return !myCommands.isEmpty();
    }
    
    public boolean hasUnexecutedCommands(){
        return !unexecutedCommands.isEmpty();
    }
}
