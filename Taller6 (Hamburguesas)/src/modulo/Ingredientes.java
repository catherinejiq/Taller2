package modulo;
public class Ingredientes {

    public String nombre;
    public int costoAdicional;
    public int calorias;

    public Ingredientes(String nombre, int costoAdicional, int calorias) {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
        this.calorias = calorias;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCostoAdicional() {
        return costoAdicional;
    }

    public int getCalorias() {
        return calorias;
    }
}