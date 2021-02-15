/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model.table;

import communcation.Communcation;
import domain.Product;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class ProductTableModel extends AbstractTableModel{
    ArrayList<Product> productsCopy;
    ArrayList<Product> products;
    String sortValue = "";
    String[] columnNames = {"Name", "Type", "Unit", "User", "Price"};
    
    
    public ProductTableModel(ArrayList<Product> products) {
        this.products = products;
        this.productsCopy = new ArrayList<>(products);
    }
    
    
    @Override
    public int getRowCount() {
        return productsCopy.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productsCopy.get(rowIndex);
        switch(columnIndex){
            case 0:
                return product.getName();
            case 1:
                return product.getType();
            case 2:
                return product.getUnit();
            case 3:
                return product.getUser().toString();
            case 4:
                return product.getPrice();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void sort(){
        productsCopy = new ArrayList<>();
        for (Product product : products) {
            if(product.getName().contains(sortValue)){
                productsCopy.add(product);
            }
        }
        fireTableDataChanged();
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
    
    

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void refresh() throws Exception {
        products = Communcation.getInstance().getAllProducts();
        sort();
    }

  

    
    
}

