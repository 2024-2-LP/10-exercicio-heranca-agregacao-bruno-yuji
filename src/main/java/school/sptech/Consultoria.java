package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public Consultoria() {}

    public void contratar(Desenvolvedor desenvolvedor) {
        if (vagas > 0 && desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            contratar(desenvolvedor);
        }
    }

    public Double getTotalSalarios() {
        Double somaSalario = 0.0;
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            somaSalario += desenvolvedor.calcularSalario();
        }
        return somaSalario;
    }

    public Integer qtdDesenvolvedoresMobile() {
        Integer qtdDevMob = 0;
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor instanceof DesenvolvedorMobile) {
                qtdDevMob++;
            }
        }
        return qtdDevMob;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> listaDevs = new ArrayList<>();
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor.calcularSalario() > salario) {
                listaDevs.add(desenvolvedor);
            }
        }
        return listaDevs;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (!desenvolvedores.isEmpty()) {
            Desenvolvedor devMenorSalario = desenvolvedores.get(0);
            for (Desenvolvedor desenvolvedor : desenvolvedores) {
                if (desenvolvedor.calcularSalario() < devMenorSalario.calcularSalario()) {
                    devMenorSalario = desenvolvedor;
                }
            }
            return devMenorSalario;
        }
        return null;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> listaDevs = new ArrayList<>();
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor instanceof DesenvolvedorMobile) {
                if (((DesenvolvedorMobile) desenvolvedor).getLinguagem().equals(tecnologia) ||
                ((DesenvolvedorMobile) desenvolvedor).getPlataforma().equals(tecnologia)) {
                    listaDevs.add(desenvolvedor);
                }
            } else if (desenvolvedor instanceof DesenvolvedorWeb) {
                if (((DesenvolvedorWeb) desenvolvedor).getBackend().equals(tecnologia) ||
                ((DesenvolvedorWeb) desenvolvedor).getFrontend().equals(tecnologia) ||
                ((DesenvolvedorWeb) desenvolvedor).getSgbd().equals(tecnologia)) {
                    listaDevs.add(desenvolvedor);
                }
            }
        }
        return listaDevs;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        List<Desenvolvedor> listaDev = buscarPorTecnologia(tecnologia);
        Double somaSalario = 0.0;
        for (Desenvolvedor desenvolvedor : listaDev) {
            somaSalario += desenvolvedor.calcularSalario();
        }
        return somaSalario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }
}
