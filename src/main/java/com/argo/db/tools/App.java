package com.argo.db.tools;

import com.argo.db.tools.mysql.MySqlTableServiceImpl;
import org.apache.commons.cli.*;

import java.util.List;

/**
 * Created by yamingd on 9/17/15.
 */
public class App {

    private static Options prepareOptions() {

        Option mode = Option.builder("a")
                .hasArg()
                .required(true)
                .desc("action name, such as xls, app")
                .build();

        Option confFile = Option.builder("c")
                .hasArg()
                .required(true)
                .desc("conf yaml file name")
                .build();

        Option db = Option.builder("d")
                .hasArg()
                .desc("db name")
                .build();

        Option table = Option.builder("t")
                .hasArg()
                .desc("table name")
                .build();

        Option output = Option.builder("o")
                .hasArg()
                .desc("output dir")
                .build();

        Options options = new Options();

        options.addOption(confFile);
        options.addOption(db);
        options.addOption(table);
        options.addOption(output);

        return options;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        Options options = prepareOptions();
        CommandLine line = null;
        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            line = parser.parse(options, args);
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
            return;
        }

        ProjectInfo projectInfo;

        if (line.hasOption("c")) {
            projectInfo = ToolsConfig.load(line.getOptionValue("c"));
        }else{
            projectInfo = ToolsConfig.load(ToolsConfig.confName);
        }

        String action = line.getOptionValue("a");

        TableSchemaService tableSchemaService = new MySqlTableServiceImpl();
        tableSchemaService.openConnection(projectInfo.getDburl());

        if ("xls".equalsIgnoreCase(action)) {
            List<String> dbname = projectInfo.getDbs();
            for (String name : dbname) {
                tableSchemaService.exportXls(name, "simple");
                tableSchemaService.exportXls(name, "full");
            }
        }else{

            ExportParams params = new ExportParams(line);
            tableSchemaService.exportEntity(params);

        }
    }

}
