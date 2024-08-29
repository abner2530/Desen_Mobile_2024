package aluno.dsmobile.aula_mvvm.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import aluno.dsmobile.aula_mvvm.BR;
import aluno.dsmobile.aula_mvvm.Model.Nota;

public class NotaViewModel extends BaseObservable {

    private Nota nota;

    public NotaViewModel() {
        nota = new Nota("Abner", "Enoque");
    }

    @Bindable
    public String getNotaTitulo() {
        return nota.getTitulo();
    }

    @Bindable
    public String getNotaDescricao() {
        return nota.getDescricao();
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
        notifyPropertyChanged(BR.notaTitulo);
        notifyPropertyChanged(BR.notaDescricao);
    }

    public void setNotaDetails(String titulo, String descricao) {
        this.nota.setTitulo(titulo);
        this.nota.setDescricao(descricao);
        notifyPropertyChanged(BR.notaTitulo);
        notifyPropertyChanged(BR.notaDescricao);
    }

    public void validadeTextField() {

    }
}