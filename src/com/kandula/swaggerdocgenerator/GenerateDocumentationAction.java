package com.kandula.swaggerdocgenerator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class GenerateDocumentationAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        PsiClass parent = getParentOfPsiFile(e);
        GenerateDialog dialog = new GenerateDialog(parent);
        dialog.show();
        if(dialog.isOK()){
            List<PsiField> fields = dialog.getPsiFields();
            List<PsiMember> members = dialog.getPsiMembers();
            if(CollectionUtils.isNotEmpty(fields)){
                generateDocumentation(fields, members, parent);
            }
        }
    }

    private void generateDocumentation(List<PsiField> fields, List<PsiMember> members, PsiClass parent) {

        new WriteCommandAction.Simple(parent.getProject(), parent.getContainingFile()){
            @Override
            protected void run() throws Throwable {
                //new DocumentationGeneratorForVariables(fields, parent);
                new DocumentationGeneratorForClass(members, parent);
            }
        }.execute();

    }


    @Override
    public void update(AnActionEvent e) {

        PsiClass parentOfPsiFile = getParentOfPsiFile(e);
        if (parentOfPsiFile == null) {
            e.getPresentation().setEnabled(false);
            return;
        }
        e.getPresentation().setEnabled(true);
    }

    private PsiClass getParentOfPsiFile(AnActionEvent e) {

        PsiFile file = e.getData(LangDataKeys.PSI_FILE);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (file == null || editor == null) {
            e.getPresentation().setEnabled(false);
            return null;
        }
        int offset = getOffset(editor);
        PsiElement element = getElementAtOffset(file, offset);
        PsiClass parent = PsiTreeUtil.getParentOfType(element, PsiClass.class);
        return parent;
    }

    private PsiElement getElementAtOffset(PsiFile file, int offset) {
        return file.findElementAt(offset);
    }

    private int getOffset(Editor editor) {
        return editor.getCaretModel().getOffset();
    }
}
