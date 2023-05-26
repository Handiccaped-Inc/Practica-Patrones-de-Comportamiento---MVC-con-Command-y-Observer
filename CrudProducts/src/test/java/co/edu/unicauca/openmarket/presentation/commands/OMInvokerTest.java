import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.edu.unicauca.openmarket.presentation.commands.OMCommand;
import co.edu.unicauca.openmarket.presentation.commands.OMInvoker;

public class OMInvokerTest {

    private OMInvoker invoker;
    private AddCommand command;
    
    @BeforeEach
    void setUp() {
        invoker = new OMInvoker();
        command = new AddCommand(2, 3); // Custom implementation of OMCommand for addition
    }
    
    @Test
    void testAddCommand() {
        invoker.addCommand(command);
        
        // Verify that the current command is set correctly
        assertEquals(command, invoker.getCurrentCommand());
    }
    
    @Test
    void testExecute() {
        invoker.addCommand(command);
        
        invoker.execute();
        
        // Verify that the command is made (executed)
        assertTrue(command.isExecuted());
        
        // Verify that the executed command is added to the list of myCommands
        List<OMCommand> myCommands = invoker.getExecutedCommands();
        assertTrue(myCommands.contains(command));
        
        // Verify that the list of unexecutedCommands is empty
        List<OMCommand> unexecutedCommands = invoker.getUnexecutedCommands();
        assertTrue(unexecutedCommands.isEmpty());
    }
    
    @Test
    void testUnexecute() {
        invoker.addCommand(command);
        invoker.execute();
        
        invoker.unexecute();
        
        // Verify that the command is unmade (unexecuted)
        assertFalse(command.isExecuted());
        
        // Verify that the unexecuted command is removed from the list of myCommands
        List<OMCommand> myCommands = invoker.getExecutedCommands();
        assertFalse(myCommands.contains(command));
        
        // Verify that the unexecuted command is added to the list of unexecutedCommands
        List<OMCommand> unexecutedCommands = invoker.getUnexecutedCommands();
        assertTrue(unexecutedCommands.contains(command));
    }
    
    @Test
    void testReexecute() {
        invoker.addCommand(command);
        invoker.execute();
        invoker.unexecute();
        
        invoker.reexecute();
        
        // Verify that the command is remade (reexecuted)
        assertTrue(command.isExecuted());
        
        // Verify that the reexecuted command is added back to the list of myCommands
        List<OMCommand> myCommands = invoker.getExecutedCommands();
        assertTrue(myCommands.contains(command));
        
        // Verify that the reexecuted command is removed from the list of unexecutedCommands
        List<OMCommand> unexecutedCommands = invoker.getUnexecutedCommands();
        assertFalse(unexecutedCommands.contains(command));
    }
    
    @Test
    void testHasMoreCommands() {
        assertFalse(invoker.hasMoreCommands());
        
        invoker.addCommand(command);
        invoker.execute();
        assertTrue(invoker.hasMoreCommands());
    }
    
    @Test
    void testHasUnexecutedCommands() {
        assertFalse(invoker.hasUnexecutedCommands());
        
        invoker.addCommand(command);
        invoker.execute();
        
        assertFalse(invoker.hasUnexecutedCommands());
        
        invoker.unexecute();
        
        assertTrue(invoker.hasUnexecutedCommands());
    }
    
    // Custom implementation of OMCommand for addition
    private class AddCommand extends OMCommand {
        private int a;
        private int b;
        private boolean executed;
        
        public AddCommand(int a, int b) {
            this.a = a;
            this.b = b;
            this.executed = false;
        }
        
        public boolean isExecuted() {
            return executed;
        }
        
        @Override
        public void make() {
            // Perform addition
            int result = a + b;
            System.out.println("Addition Result: " + result);
            executed = true;
        }

        @Override
        public void unmake() {
            // Undo addition
            System.out.println("Undo Addition");
            executed = false;
        }

        @Override
        public void remake() {
            // Re-execute addition
            System.out.println("Redo Addition");
            executed = true;
        }
    }
}
