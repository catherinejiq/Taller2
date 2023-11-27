package modulo;

@SuppressWarnings("serial")

public class IngredientesRepException extends HamburguesasException{
    private String repetido;

    public IngredientesRepException(String repetido) {
        super("Error: Ingrediente repetido - " + repetido);
        this.repetido = repetido;
    }

    public String getrepetido() {
        return repetido;
    }
}