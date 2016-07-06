/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.impl.hazelcast.client;

import com.hazelcast.client.spi.ClientProxy;
import com.hazelcast.client.spi.ClientProxyDescriptor;
import com.hazelcast.client.spi.ClientProxyDescriptorProvider;
import com.hazelcast.jet.impl.hazelcast.JetService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("EI_EXPOSE_REP")
public class ApplicationClientProxyDescriptorProvider implements ClientProxyDescriptorProvider {
    private final ClientProxyDescriptor[] descriptors;

    public ApplicationClientProxyDescriptorProvider() {
        this.descriptors = new ClientProxyDescriptor[1];
        this.descriptors[0] = new ApplicationProxyDescriptor();
    }

    @Override
    public ClientProxyDescriptor[] createClientProxyDescriptors() {
        return this.descriptors;
    }

    public static class ApplicationProxyDescriptor implements ClientProxyDescriptor {
        @Override
        public String getServiceName() {
            return JetService.SERVICE_NAME;
        }

        @Override
        public Class<? extends ClientProxy> getClientProxyClass() {
            return ClientApplicationProxy.class;
        }
    }

}