
class T7_4 {
    
	class ResourceInitializationException extends Exception {}
	class NonInitializableResource implements AutoCloseable {
		public NonInitializableResource() throws ResourceInitializationException {
			throw new ResourceInitializationException();
		}
		public void close() {}
	}

	public void pass() {
		try (NonInitializableResource r = new NonInitializableResource()) {
		} catch (ResourceInitializationException e) {
		}
	}

}
