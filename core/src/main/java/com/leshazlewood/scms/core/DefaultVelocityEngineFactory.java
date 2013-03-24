/*
 * Copyright 2013 Les Hazlewood
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leshazlewood.scms.core;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

import java.io.File;
import java.util.Properties;

/**
 * @since 0.1
 */
public class DefaultVelocityEngineFactory implements VelocityEngineFactory {

    private File sourceDir;
    private File templatesDir;

    public DefaultVelocityEngineFactory(File sourceDir, File templatesDir) {
        this.sourceDir = sourceDir;
        this.templatesDir = templatesDir;
    }

    private String createResourceLoaderPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(sourceDir.getAbsolutePath()).append(", ").append(templatesDir.getAbsolutePath());
        return sb.toString();
    }


    @Override
    public VelocityEngine createVelocityEngine() {
        Properties props = new Properties();
        props.put("resource.loader", "file");
        props.put("file.resource.loader.class", FileResourceLoader.class.getName());
        props.put("file.resource.loader.path", createResourceLoaderPath());
        props.put("file.resource.loader.cache", "false");

        VelocityEngine engine = new VelocityEngine(props);
        engine.init();

        return engine;
    }
}
