public class Ropa {
    
    private String tipoPrenda;
    private String color;
    private int nivelSuciedad;

    public Ropa(String pprenda, String pcolor, int pnivel){
        this.setTipoPrenda(pprenda);
        this.setColor(pcolor);
        this.setNivelSuciedad(pnivel);
    }
    
    public Ropa(){
        this("","",5);
    }
    
    public Ropa(String pprenda){
        this(pprenda, "", 5);
    }
    
    public Ropa(int pnivel){
        this("","", pnivel);
    }
        
    /**
     * @return the tipoPrenda
     */
    public String getTipoPrenda() {
        return tipoPrenda;
    }

    /**
     * @param tipoPrenda the tipoPrenda to set
     */
    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the nivelSuciedad
     */
    public int getNivelSuciedad() {
        return nivelSuciedad;
    }

    /**
     * @param nivelSuciedad the nivelSuciedad to set
     */
    public void setNivelSuciedad(int nivelSuciedad) {
        this.nivelSuciedad = nivelSuciedad;
    }
    
    
    public void reducirNivelSuciedad(int ppotencia){
        if(getNivelSuciedad() >= 0){
            if((getNivelSuciedad() - ppotencia) >= 0){
                setNivelSuciedad(getNivelSuciedad() - ppotencia);
            }
        }
    } 
    
    public String clasificarNivelSuciedad(){
        String resul = "";
        if(this.getNivelSuciedad() >= 7){
            resul = "Muy sucia";
        }else{
            if(this.getNivelSuciedad() >= 4){
                resul = "Sucia";
            }else{
                if(this.getNivelSuciedad() >= 1){
                    resul = "Ligeramente sucia";
                }else{
                    resul = "Limpia";
                }
            }
        }
        return resul;
    }
    
    public String toString(){
        String resul = "";
        String miColor = this.getColor() + "\n";
        String miTipo = this.getTipoPrenda() + "\n";
        String miSuciedad = clasificarNivelSuciedad();
        resul = miColor + miTipo + miSuciedad;
        return resul;
    }
    
    
    

}
