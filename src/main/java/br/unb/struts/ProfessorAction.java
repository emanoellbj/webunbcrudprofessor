package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.unb.dao.ProfessorDAO;
import br.unb.dominio.Professor;

public class ProfessorAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("ProfessorAction::execute");
    	ProfessorForm professorForm = (ProfessorForm) form;
        ProfessorDAO professorDAO = new ProfessorDAO();

        ActionMessages errors = new ActionErrors();
        if (professorForm.getNome() == null || professorForm.getNome().trim().isEmpty()) {
            errors.add("nome", new ActionMessage("error.nome.required"));
        }

        if (!errors.isEmpty()) {
            // Se houver erros, adicione-os ao request e retorne a página de entrada (input)
            saveErrors(request, errors);
//            return mapping.findForward("success");
        }
        String method = request.getParameter("method");
        System.out.println("ProfessorAction::execute::method "+ method);
        if (method != null && !method.isEmpty()) {
            switch (method) {
                case "salvar":
                    Professor professor = new Professor(professorForm.getNome());
                    professorDAO.salvar(professor);
                    break;
                case "editar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    professor = professorDAO.getById(id);
                    professorForm.setId(professor.getId());
                    professorForm.setNome(professor.getNome());
                    break;
                case "atualizar":
                    professor = new Professor(professorForm.getId(), professorForm.getNome());
                    professorDAO.update(professor);
                    break;
                case "excluir":
                    id = Integer.parseInt(request.getParameter("id"));
                    professorDAO.delete(id);
                    break;
            }
        }

        List<Professor> professores = professorDAO.findAll();
        request.setAttribute("professores", professores);

        return mapping.findForward("success");
    }
}