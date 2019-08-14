package com.facdjunior.comercial.bean;

import com.facdjunior.comercial.domain.Estado;
import com.facdjunior.comercial.dao.EstadoDAO;
import com.facdjunior.comercial.util.HibernateUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author Francisco Junior
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

    private Estado estado;
    private List<Estado> estados;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @PostConstruct
    public void listar() {
        try {
            EstadoDAO estadosDAO = new EstadoDAO();
            estados = estadosDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
            erro.printStackTrace();
        }
    }

    public void novo() {
        estado = new Estado();
    }

    public void salvar() {

        try {

            EstadoDAO estadosDAO = new EstadoDAO();
            estadosDAO.merge(estado);

            novo();
            listar();
            
            Messages.addGlobalInfo("Registro gravado com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar registro");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");

			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			estados = estadoDAO.listar();

			Messages.addGlobalInfo("Estado removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	}
        
        public void imprimir(){
            try{
            String relatorio = Faces.getRealPath("/reports/estados.jasper");
            Map<String, Object> parametros = new HashMap<>();
            
            Connection conexao = HibernateUtil.getConexao();
            
            
            JasperFillManager.fillReport(relatorio, parametros, conexao);
                JasperPrintManager.printReport(relatorio, false);
            
            }catch(JRException erro){
                Messages.addGlobalError("Ocorreu um erro ao tentar gerar relatório");
            }
        }
}
