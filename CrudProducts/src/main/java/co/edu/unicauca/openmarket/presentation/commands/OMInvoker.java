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
    
    private List<OMCommand> executedCommands;
    private List<OMCommand> unexecutedCommands;
    private OMCommand currentCommand;
    
    public OMInvoker(){
        executedCommands = new ArrayList<>();
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
        executedCommands.add(currentCommand);
        //Se limpia la lista de comandos desejecutados (Cuando se realiza una accion esos comandos se pierden, ese es el comportamiento normal de
        // las apps que conocemos, si deshacemos algo, y hacemos algo, esos que deshicimos se perdera)
        unexecutedCommands.clear();
    }
    
    public void unexecute(){
        if (!executedCommands.isEmpty()){
            //Obtener el indice del ultimo comando ejecutado
            int index = executedCommands.size()-1;
            //Obtener ese ultimo comando
            OMCommand unexecutedCommand= executedCommands.get(index);
            //Deshacerlo
            unexecutedCommand.unmake();
            //Remover el comando deshecho
            executedCommands.remove(index);
            //Añadir el comando a los comandos deshechos
            unexecutedCommands.add(unexecutedCommand);
        }
        
    }
    
    //La implementacion es bastante parecida a la de execute, sin embargo no se puede reusar execute ya que su ultima linea es destructiva
    public void reexecute(){
        if(!unexecutedCommands.isEmpty()){
            int index = unexecutedCommands.size()-1;
            //Obtener el ultimo comando deshecho
            OMCommand command= unexecutedCommands.get(index); 
            //Establecerlo como el comand   o actual
            this.addCommand(command);
            //Ejecutar el comando actual
            currentCommand.remake();
            //Se añade al historial de comandos ejecutados
            executedCommands.add(currentCommand);
            //Remover el comando rehecho de la lista de desejecutados
            unexecutedCommands.remove(index);
        }
    }
    
    
    
    public boolean hasMoreCommands(){
        return !executedCommands.isEmpty();
    }
    
    public boolean hasUnexecutedCommands(){
        return !unexecutedCommands.isEmpty();
    }

    public Object getCurrentCommand() {
        return currentCommand;
    }

    public List<OMCommand> getExecutedCommands() {
        return executedCommands;
    }

    public void setExecutedCommands(List<OMCommand> executedCommands) {
        this.executedCommands = executedCommands;
    }

    public List<OMCommand> getUnexecutedCommands() {
        return unexecutedCommands;
    }

    public void setUnexecutedCommands(List<OMCommand> unexecutedCommands) {
        this.unexecutedCommands = unexecutedCommands;
    }
    
    
}
