
class T7_3 {
    
	class ResourceInitializationException extends Exception {}
	class NonInitializableResource implements AutoCloseable {
		public NonInitializableResource() throws ResourceInitializationException {
			throw new ResourceInitializationException();
		}
		public void close() {}
	}

	public void foo() {
		try (NonInitializableResource r = new NonInitializableResource()) {
		}
	}

}
