package balanceoflife;

public class PersonalData {

    private String nimi;
    private int paino;
    private int pituus;

    public PersonalData(String nimi, int pituus, int paino) {
        this.nimi = nimi;
        this.paino = paino;
        this.pituus = pituus;
    }

    public int getPaino() {
        return paino;
    }

    public int getPituus() {
        return pituus;
    }

    //Nick Trefethen new formula
    //http://people.maths.ox.ac.uk/trefethen/bmi.html
    //http://people.maths.ox.ac.uk/trefethen/bmi_calc.html
    //Current formula: BMI = weight(kg)/height(m)^2 = 703*weight(lb)/height(in)^2.
    //New formula: BMI = 1.3*weight(kg)/height(m)^2.5 = 5734*weight(lb)/height(in)^2.5
    public double painoindeksi() {
        return 1.3 * paino / (Math.pow(pituus * 0.01, 2.5));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nHei ").append(nimi).append(" ja tervetuloa käyttämään Elämän balanssi sovellusta!\n");
        sb.append("\n").append(nimi).append(" pituus ").append(pituus).append("cm ja paino ").append(paino).append("kg\n");
        sb.append("Nick Trefethen laskukaavan mukaan laskettu painoindeksi on ").append(painoindeksi());

        if (painoindeksi() > 18.5 && painoindeksi() < 25) {
            sb.append("\n").append("\n").append("Olet normaalipainoinen.");
        } else if (painoindeksi() <= 30) {
            sb.append("\n").append("\n").append("Sinulla on ylipainoa tai lievää lihavuutta.");
        } else if (painoindeksi() <= 35) {
            sb.append("\n").append("Sinulla on merkittävää lihavuutta.");
        } else if (painoindeksi() <= 40) {
            sb.append("\n").append("Sinulla on vaikeaa lihavuutta.");
        } else if (painoindeksi() > 40) {
            sb.append("\n").append("Sinulla on sairaalloista lihavuutta.");
        }
        return sb.toString();
    }

}
