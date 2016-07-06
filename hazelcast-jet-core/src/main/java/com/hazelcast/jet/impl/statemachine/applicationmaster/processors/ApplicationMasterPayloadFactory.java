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

package com.hazelcast.jet.impl.statemachine.applicationmaster.processors;

import com.hazelcast.jet.impl.container.ApplicationMaster;
import com.hazelcast.jet.impl.container.applicationmaster.ApplicationMasterEvent;
import com.hazelcast.jet.impl.container.ContainerPayloadProcessor;

public final class ApplicationMasterPayloadFactory {
    private ApplicationMasterPayloadFactory() {
    }

    public static ContainerPayloadProcessor getProcessor(ApplicationMasterEvent event,
                                                         ApplicationMaster applicationMaster) {
        switch (event) {
            case SUBMIT_DAG:
                return new ExecutionPlanBuilderProcessor(applicationMaster);
            case EXECUTE:
                return new ExecuteApplicationProcessor(applicationMaster);
            case INTERRUPT_EXECUTION:
                return new InterruptApplicationProcessor(applicationMaster);
            case EXECUTION_ERROR:
                return new ExecutionErrorProcessor(applicationMaster);
            case EXECUTION_COMPLETED:
                return new ExecutionCompletedProcessor();
            case FINALIZE:
                return new FinalizeApplicationProcessor(applicationMaster);
            default:
                return null;
        }
    }
}