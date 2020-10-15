package co.edu.sena.oftalmologia;

public class Oftalmologia  {
    private String nombre;
    private String direccion;
    private Ubicacion ubicacion;
    private String horario;
    private String foto;
  //  private double distancia;

    public Oftalmologia(String nombre, String direccion, Ubicacion ubicacion, String horario, String foto) {
        this.nombre=nombre;
        this.direccion=direccion;
        this.ubicacion=ubicacion;
        this.horario=horario;
        this.foto=foto;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

   /* public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public int compareTo(Object o) {
        double distancia = ((Oftalmologia) o).getDistancia();
        double resultado = this.distancia-distancia;
        return (int) resultado;
    }*/

    @Override
    public String toString() {
        return "Oftalmologia{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ubicacion=" + ubicacion +
                ", horario='" + horario + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
