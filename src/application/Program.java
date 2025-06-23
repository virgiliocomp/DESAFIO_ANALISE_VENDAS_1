package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Entre o caminho do arquivo: ");
        String path = sc.nextLine();

        try {

            List<Sale> sales = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null){
                String[] lineContent = line.split(",");
                int month = Integer.parseInt(lineContent[0]);
                int year = Integer.parseInt(lineContent[1]);
                String seller = lineContent[2];
                int items = Integer.parseInt(lineContent[3]);
                double total = Double.parseDouble(lineContent[4]);

                sales.add(new Sale(month,year, seller, items, total));
            }

            List<Sale> fiveFirstSalesWithHighestAverage = sales.stream()
                    .filter(x -> x.getYear() == 2016)
                    .sorted(Comparator.comparingDouble(Sale::averagePrice).reversed())
                    .limit(5)
                    .toList();

            System.out.println("\nCinco primeiras vendas de 2016 de maior preço médio");
            fiveFirstSalesWithHighestAverage.forEach(System.out::println);

            double loganTotalSales = sales.stream()
                    .filter(x -> x.getSeller().equals("Logan"))
                    .filter(x -> x.getMonth() == 1 || x.getMonth() == 7)
                    .mapToDouble(Sale::getTotal)
                    .sum();

            System.out.println("\nValor total vendido pelo vendedor Logan nos meses 1 e 7 = " + loganTotalSales);

        }
        catch (IOException e){
            System.out.println("Erro: " + path + " (O sistema não pode encontrar o arquivo especificado");
        }


    }
}
