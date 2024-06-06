package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.ui.UI;

import java.util.List;

public class Main {
    private static final System system = new System("Main System", 0);

    public static void main(String[] args) {
        populateSystem();

        new UI(system).init();
    }

    public static void populateSystem() {
        //Chatbot 0
        Option op1 = new Option(1, "1) Viajar", 1, 1, List.of("viajar", "turistear", "conocer"));
        Option op2 = new Option(2, "2) Estudiar", 2, 1, List.of("estudiar", "aprender", "perfeccionarme"));
        Flow fl1 = new Flow(1, "Flujo Principal Chatbot 1\nBienvenido\n¿Qué te gustaría hacer?",
                List.of(op1));
        fl1.flowAddOption(op2);
        Chatbot cb0 = new Chatbot(0, "Inicial", "Bienvenido\n¿Qué te gustaría hacer?", 1, List.of(fl1));

        //Chatbot 1
        Option op3 = new Option(1, "1) New York, USA", 1, 2, List.of("USA", "Estados Unidos", "New York"));
        Option op4 = new Option(2, "2) París, Francia", 1, 1, List.of("Paris", "Eiffel"));
        Option op5 = new Option(3, "3) Torres del Paine, Chile", 1, 1,
                List.of("Chile", "Torres", "Paine", "Torres Paine", "Torres del Paine"));
        Option op6 = new Option(4, "4) Volver", 0, 1, List.of("Regresar", "Salir", "Volver"));
        //Opciones segundo flujo Chatbot1
        Option op7 = new Option(1, "1) Central Park", 1, 2, List.of("Central", "Park", "Central Park"));
        Option op8 = new Option(2, "2) Museos", 1, 2, List.of("Museo"));
        Option op9 = new Option(3, "3) Ningún otro atractivo", 1, 3, List.of("Ninguno"));
        Option op10 = new Option(4, "4) Cambiar destino", 1, 1, List.of("Cambiar", "Volver", "Salir"));
        Option op11 = new Option(1, "1) Solo", 1, 3, List.of("Solo"));
        Option op12 = new Option(2, "2) En pareja", 1, 3, List.of("Pareja"));
        Option op13 = new Option(3, "3) En familia", 1, 3, List.of("Familia"));
        Option op14 = new Option(4, "4) Agregar más atractivos", 1, 2, List.of("Volver", "Atractivos"));
        Option op15 = new Option(5, "5) En realidad quiero otro destino", 1, 1, List.of("Cambiar destino"));
        Flow fl2 = new Flow(1, "Flujo 1 Chatbot1\n¿Dónde te Gustaría ir?", List.of(op3, op4, op5, op6));
        Flow fl3 = new Flow(2, "Flujo 2 Chatbot1\n¿Qué atractivos te gustaría visitar?", List.of(op7, op8, op9, op10));
        Flow fl4 = new Flow(3, "Flujo 3 Chatbot1\n¿Vas solo o acompañado?", List.of(op11, op12, op13, op14, op15));
        Chatbot cb1 = new Chatbot(1, "Orientador Académico", "Bienvenido\n¿Dónde quieres viajar?", 1,
                List.of(fl2, fl3, fl4));

        //Chatbot 2
        Option op16 = new Option(1, "1) Carrera Técnica", 2, 1, List.of("Técnica"));
        Option op17 = new Option(2, "2) Postgrado", 2, 1, List.of("Doctorado", "Magister", "Postgrado"));
        Option op18 = new Option(3, "3) Volver", 0, 1, List.of("Volver", "Salir", "Regresar"));
        Flow fl5 = new Flow(1, "Flujo 1 Chatbot2\n¿Qué te gustaría estudiar?", List.of(op16, op17, op18));
        Chatbot cb2 = new Chatbot(2, "Agencia Viajes", "Bienvenido\n¿Dónde quieres viajar?", 1, List.of(fl5));

        //Sistema
        system.systemAddChatbot(cb0);
        system.systemAddChatbot(cb1);
        system.systemAddChatbot(cb2);

        system.systemAddUser(new User("user1"));
        system.systemAddUser(new User("user2"));
        system.systemAddUser(new User("user3"));

        system.systemLogin("user1");
        system.systemTalk("hola");
        system.systemTalk("1");
        system.systemTalk("1");
        system.systemTalk("Museo");
        system.systemTalk("1");
        system.systemTalk("2");
        system.systemTalk("4");
        system.systemLogout();

        system.systemLogin("user2");
        system.systemSimulate(5, 12345);
        system.systemLogout();

        system.systemAddUser(new Admin("admin"));
    }
}