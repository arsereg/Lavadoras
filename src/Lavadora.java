
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

    public class Lavadora implements Runnable{

    /**
     * @return the cantLavadoras
     */
    private static int getCantLavadoras() {
        return cantLavadoras;
    }

    /**
     * @param aCantLavadoras the cantLavadoras to set
     */
    private static void setCantLavadoras(int aCantLavadoras) {
        cantLavadoras = aCantLavadoras;
    }

    /**
     * @return the maxVolumen
     */
    private static double getMaxVolumen() {
        return maxVolumen;
    }
    
    private Ropa prenda;
    private String modelo;
    private String numSerie;
    private int potencia;
    private int tiempoRestante;
    private boolean detergenteCargado;
    private volatile boolean lavando;
    private double volumenActual;
    private boolean pausada;
    private static int cantLavadoras = 0;
    private static double maxVolumen = 100;
    private Thread t;
    
    public Lavadora(){
        prenda = null;
        generarSerie();
        setModelo("LV");
        setTiempoRestante(0);
        setDetergenteCargado(false);
        setVolumenActual(0);
        setPotencia(1);
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    /**
     * @return the prenda
     */
    public Ropa getPrenda() {
        return prenda;
    }

    /**
     * @param prenda the prenda to set
     */
    public void setPrenda(Ropa prenda) {
        this.prenda = prenda;
    }

    /**
     * @return the numSerie
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * @param numSerie the numSerie to set
     */
    private void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /**
     * @return the tiempoRestante
     */
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    /**
     * @param tiempoRestante the tiempoRestante to set
     */
    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    /**
     * @return the detergenteCargado
     */
    public boolean isDetergenteCargado() {
        return detergenteCargado;
    }

    /**
     * @param detergenteCargado the detergenteCargado to set
     */
    private void setDetergenteCargado(boolean detergenteCargado) {
        this.detergenteCargado = detergenteCargado;
    }

    /**
     * @return the volumenActual
     */
    public double getVolumenActual() {
        return volumenActual;
    }

    /**
     * @param volumenActual the volumenActual to set
     */
    public void setVolumenActual(double volumenActual) {
        this.volumenActual = volumenActual;
    }
    
    private static void aumentarCantLavadoras(){
        cantLavadoras++;
    }
    
    private void generarSerie(){
        String prefijo = getModelo() + "-";
        String serie = prefijo + cantLavadoras;
        this.setNumSerie(serie);
    }

    /**
     * @return the lavando
     */
    public boolean isLavando() {
        return lavando;
    }

    /**
     * @param lavando the lavando to set
     */
    private void setLavando(boolean lavando) {
        this.lavando = lavando;
    }
    
    /**
     * @return the potencia
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    private void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    
    public void drenar(){
        this.setVolumenActual(0);
    }
    
    public String toString(){
        String resul = "";
        String tipoPrenda = (prenda == null) ? "No hay prenda cargada" : prenda.getTipoPrenda();
        resul = "Prenda cargada: " + tipoPrenda + "\n";
        resul += "Modelo: " + this.getModelo() + "\n";
        resul += "Numero de serie: " + this.getNumSerie() + "\n";
        resul += "Potencia: " + this.getPotencia() + "\n";
        resul += "Tiempo restante: " + this.getTiempoRestante() + "\n";
        String det = (this.isDetergenteCargado()) ? "Detergente cargado" : "Detergente sin cargar";
        resul += "Detergente: " + det + "\n";
        String lav = (this.isLavando()) ? "Lavando" : "No hay nada lavandose";
        resul += "Lavando: " + lav + "\n";
        resul += "Porcentaje de agua: " + getVolumenActual() + "%" + "\n";
        String pause = (this.isPausada()) ? "Pausada" : "No est\u00e1 pausada";
        resul += "Pausa: " + pause + "\n";
        return resul;
    }
    
    /**
     * @return the pausada
     */
    private boolean isPausada() {
        return pausada;
    }

    /**
     * @param pausada the pausada to set
     */
    private void setPausada(boolean pausada) {
        this.pausada = pausada;
    }
    
    @Override
    public void run(){
        this.lavando = true;
        while(lavando){
            lavando = verificarContinuacionLavado();
            try {
                procesarRopa();
            } catch (InterruptedException ex) {
                Logger.getLogger(Lavadora.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.reducirTiempo();
        }
    }
    
    /*
    ///////////////////////////////////////////////////////////////////
    Acá comienza la logica del negocio
    ///////////////////////////////////////////////////////////////////
    */

    public void procesarRopa() throws InterruptedException{
        if(getPrenda() != null){
            if(getVolumenActual() > 70){
                if(isDetergenteCargado()){
                    getPrenda().reducirNivelSuciedad(potencia);
               }
            }
        t.sleep(1000);
        }
    }
    
    public int verificarIndiceSuciedad(){
        return getPrenda().getNivelSuciedad();
    }
    
    public void cargarAgua(double pagua){
        double sum = pagua + getVolumenActual();
        if(sum < 100){
            setVolumenActual(sum);
        }else{
            setVolumenActual(100);
        }
    }
    
    public void cargarDetergente(){
        if(!isDetergenteCargado()){
            setDetergenteCargado(true);
        }
    }
    
    public void especificarTiempo(int ptiempo){
        setTiempoRestante(ptiempo);
    }
    
    public void reducirTiempo(){
        this.setTiempoRestante(this.getTiempoRestante() - 1);
        if(this.getTiempoRestante() < 0){
            t.stop();
            t.interrupt();
        }
    }
    
    public synchronized void pausar() throws InterruptedException{
        if(!isPausada() && isLavando()){
            intentarPausar();
        }else{
            if(isLavando()){
                reanudar();
            }
        }
    }
    
    public synchronized void intentarPausar() throws InterruptedException{
        if(!isPausada() && isLavando()){
            setPausada(true);    
            t.stop();
        }
    }
    
    public void reanudar(){
        setPausada(false);
        t = new Thread(this);
        t.start();
    }
    
    public boolean verificarContinuacionLavado(){
        boolean resul;
        resul = this.getTiempoRestante() > 1;
        return resul;
    }
    
    public void iniciar(){
        t = new Thread(this);
        if(getPrenda() != null){
            if(getVolumenActual() > 70){
                if(isDetergenteCargado()){
                    this.setLavando(true);
                    t.start();
               }
            }
        }
    }
    
    public void arrancar(){
        if(this.isPausada()){
            this.reanudar();
        }else{
            this.iniciar();
        }
    }

    
}
