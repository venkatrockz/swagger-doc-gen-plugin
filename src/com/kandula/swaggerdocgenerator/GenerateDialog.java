package com.kandula.swaggerdocgenerator;

import com.intellij.ide.util.DefaultPsiElementCellRenderer;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMember;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class GenerateDialog extends DialogWrapper {

    private CollectionListModel<PsiField> fields;
    private LabeledComponent<JPanel> myComponent;
    private JList jList;

    protected GenerateDialog(PsiClass parent) {
        super(parent.getProject());
        setTitle("Select fields to generate documentation");
        this.fields = new CollectionListModel<>(parent.getAllFields());
        this.jList = new JList(fields);
        jList.setCellRenderer(new DefaultPsiElementCellRenderer());
        ToolbarDecorator toolbarDecorator = ToolbarDecorator.createDecorator(jList);
        JPanel jPanel = toolbarDecorator.createPanel();
        this.myComponent = LabeledComponent.create(jPanel, "Select fields");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return myComponent;
    }

    public List<PsiField> getPsiFields() {
        return jList.getSelectedValuesList();
    }

    public List<PsiMember> getPsiMembers() {
        return jList.getSelectedValuesList();
    }
}
    
    
