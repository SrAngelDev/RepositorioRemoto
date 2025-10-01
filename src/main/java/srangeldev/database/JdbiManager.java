package srangeldev.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import srangeldev.config.Config;
import srangeldev.dao.UserDao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JdbiManager {
    private static JdbiManager instance;
    private final Jdbi jdbi;
    private final Logger logger = LoggerFactory.getLogger(JdbiManager.class);

    /**
     * constructor privado del JDBI
     */
    private JdbiManager() {
        logger.info("Inicializando JdbiManager y configurando");
        String url = Config.getInstance().getDatabaseUrl();
        boolean initTables = Config.getInstance().isDatabaseInitTables();
        boolean initData =  Config.getInstance().isDatabaseInitData();

        this.jdbi = Jdbi.create(url);

        jdbi.installPlugin(new SqlObjectPlugin());

        if (initTables) {
            logger.info("Inicializando las tablas en la BBDD");
            executeSqlScriptFromResources("tables.sql");
        }

        if (initData) {
            logger.info("Inicializando los datos en la BBDD");
            executeSqlScriptFromResources("data.sql");
        }
    }

    /**
     * Metodo solo para test
     */
    static synchronized void resetForTesting() {
        instance = null;
    }

    /**
     * Duelve una instancia unica del JdbiManager
     * @return Instancia única del JdbiManager
     */
    public static JdbiManager getInstance() {
        if (instance == null) {
            instance = new JdbiManager();
        }
        return instance;
    }

    /**
     * Obtener el DAO de usuarios usando JDBI
     */
    public UserDao getInstanceDao() {
        logger.info("Obteniendo DAO de usuarios");
        return jdbi.onDemand(UserDao.class);
    }

    /**
     * Ejecuta un script SQL desde un recurso.
     *
     * @param resourcePath Ruta del recurso en el classpath
     */
    private void executeSqlScriptFromResources(String resourcePath) {
        logger.debug("Cargando script SQL desde recursos: " + resourcePath);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String script = reader.lines().collect(Collectors.joining("\n"));
            // Ejecuta el script en un solo handle
            jdbi.useHandle(handle -> handle.createScript(script).execute());
        } catch (Exception e) {
            logger.error("Error al ejecutar el script SQL desde recursos: {}", resourcePath, e);
            logger.error("Error: {}", e.getMessage());
        }
    }
}
