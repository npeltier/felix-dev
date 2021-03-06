/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.cm.impl;


import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.ConfigurationAdmin;


/**
 * The <code>ConfigurationAdminFactory</code> is the <code>ServiceFactory</code>
 * registered as the <code>ConfigurationAdmin</code> service responsible to
 * create the real <code>ConfiguratAdmin</code> instances returend to client
 * bundles. Each bundle gets a separate instance.
 */
class ConfigurationAdminFactory implements ServiceFactory<ConfigurationAdmin>
{

    // The configuration manager to which the configuration admin instances
    // delegate most of their work
    private final ConfigurationManager configurationManager;


    ConfigurationAdminFactory( final ConfigurationManager configurationManager )
    {
        this.configurationManager = configurationManager;
    }


    /**
     * Returns a new instance of the {@link ConfigurationAdminImpl} class for
     * the given bundle.
     */
    @Override
    public ConfigurationAdmin getService( final Bundle bundle, final ServiceRegistration<ConfigurationAdmin> registration )
    {
        return new ConfigurationAdminImpl( configurationManager, bundle );
    }


    /**
     * Disposes off the given {@link ConfigurationAdminImpl} instance as the
     * given bundle has no use of it any more.
     */
    @Override
    public void ungetService( final Bundle bundle, final ServiceRegistration<ConfigurationAdmin> registration, final ConfigurationAdmin service )
    {
        ( ( ConfigurationAdminImpl ) service ).dispose();
    }

}
